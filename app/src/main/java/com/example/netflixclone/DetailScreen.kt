package com.example.netflixclone

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.netflixclone.data.room.FavoriteViewModel
import androidx.compose.runtime.getValue

@Composable
fun DetailScreen(movieId: Int, viewModel: MainViewModel,favoriteViewModel: FavoriteViewModel) {
    val trending by viewModel.trending.collectAsState()
    val popular by viewModel.popular.collectAsState()
    val topRated by viewModel.topRated.collectAsState()
    val upcoming by viewModel.upcoming.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    val movie = remember(movieId, trending, popular, topRated, upcoming,searchResults) {
        trending
            .plus(popular)
            .plus(topRated)
            .plus(upcoming)
            .plus(searchResults)
            .find { it.id == movieId }
    }

    if (movie == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Movie not found", color = Color.White)
        }
    } else {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Text(movie.title,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
            Spacer(Modifier.height(8.dp))
            Text(movie.overview,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )

            Spacer(Modifier.height(16.dp))

            val isFavorite = favoriteViewModel.isFavorite(movie)
            IconButton(onClick = { favoriteViewModel.toggleFavorite(movie) }) {
                Icon(imageVector = if(isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favourite",
                    tint = if(isFavorite) Color.Red else Color.White
                )
            }
        }
    }
}
