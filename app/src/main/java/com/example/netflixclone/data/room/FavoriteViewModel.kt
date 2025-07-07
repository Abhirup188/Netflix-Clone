package com.example.netflixclone.data.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netflixclone.data.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repo: FavoriteRepository): ViewModel() {
    private val _favorites = MutableStateFlow<List<FavoriteMovieEntity>>(emptyList())
    val favorites: StateFlow<List<FavoriteMovieEntity>> = _favorites

    init {
        viewModelScope.launch {
            repo.favorites.collect {
                _favorites.value = it
            }
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            val entity = FavoriteMovieEntity(
                movieId = movie.id,
                title = movie.title,
                posterPath = movie.posterPath,
                overview = movie.overview
            )
            if (repo.isFavorite(movie.id)) {
                repo.removeFavorite(entity)
            } else {
                repo.addFavorite(entity)
            }
        }
    }

    fun isFavorite(movie: Movie): Boolean {
        return _favorites.value.any { it.movieId == movie.id }
    }
}