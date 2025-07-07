package com.example.netflixclone

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.netflixclone.data.room.FavoriteViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(viewModel: MainViewModel,favoriteViewModel: FavoriteViewModel,navController: NavController) {
    val queryState = remember { mutableStateOf("") }
    val localMovies by viewModel.popular.collectAsState()
    val apiMovies by viewModel.searchResults.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    val filteredLocal = localMovies.filter {
        it.title.contains(queryState.value, ignoreCase = true)
    }

    Column(
        modifier = Modifier.
        fillMaxSize()
    ) {
        TextField(
            value = queryState.value,
            onValueChange = {
                queryState.value = it
                viewModel.search(it)
            },
            placeholder = { Text("Search movies...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        when{
            isSearching -> {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(color = Color.Red)
                }
            }
            apiMovies.isEmpty() && filteredLocal.isEmpty() && queryState.value.length>=2->{
                Text("No results found",
                    color = Color.Cyan,
                    fontSize = 48.sp
                )
            }
            else ->{
                val resultsToShow = if(apiMovies.isNotEmpty()) apiMovies else filteredLocal

                LazyVerticalGrid(
                    columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(resultsToShow) { movie->
                        MovieItem(movie,favoriteViewModel,navController)
                    }
                }
            }
        }
    }
}
