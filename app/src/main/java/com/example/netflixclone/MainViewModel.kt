package com.example.netflixclone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netflixclone.data.model.Movie
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MovieRepository):ViewModel() {
    private val _trending = MutableStateFlow<List<Movie>>(emptyList())
    val trending: StateFlow<List<Movie>> = _trending

    private val _popular = MutableStateFlow<List<Movie>>(emptyList())
    val popular: StateFlow<List<Movie>> = _popular

    private val _topRated = MutableStateFlow<List<Movie>>(emptyList())
    val topRated: StateFlow<List<Movie>> = _topRated

    private val _upcoming = MutableStateFlow<List<Movie>>(emptyList())
    val upcoming: StateFlow<List<Movie>> = _upcoming

    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
    val searchResults: StateFlow<List<Movie>> = _searchResults

    private var searchJob:Job? = null
    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    init {
        loadAll()
    }

    fun loadAll(){
        try {
            viewModelScope.launch {
                _trending.value = repo.getTrending()
                _popular.value = repo.getPopular()
                _topRated.value = repo.getTopRated()
                _upcoming.value = repo.getUpcoming()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun search(query: String){
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            if(query.length>=2){
                _isSearching.value = true
                try{
                    val result = repo.searchMovies(query)
                    _searchResults.value = result
                    println("TMDb search result: ${result.size}")
                }catch (e: Exception){
                    println("Search Failed:${e.message}")
                    _searchResults.value = emptyList()
                }finally {
                    _isSearching.value = false
                }
            }else{
                _searchResults.value = emptyList()
                _isSearching.value = false
            }
        }
    }
}