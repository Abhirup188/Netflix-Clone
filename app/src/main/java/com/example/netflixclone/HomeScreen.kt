package com.example.netflixclone

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.netflixclone.Authentication.AuthViewModel
import com.example.netflixclone.data.room.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel,authViewModel: AuthViewModel,
               navController: NavController,
               favoriteViewModel: FavoriteViewModel
){
    val trendingMovies by viewModel.trending.collectAsState()
    val popularMovies by viewModel.popular.collectAsState()
    val topRatedMovies by viewModel.topRated.collectAsState()
    val upcomingMovies by viewModel.upcoming.collectAsState()

    LaunchedEffect(Unit) {
        try{
            viewModel.loadAll()
        }catch (e: Exception){
            print(e.localizedMessage)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Netflix", color = Color.Red) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
            )
        }
    ) {
        LazyColumn(modifier = Modifier
            .padding(it)
        ) {
            item{
                Text(text = "Trending Movies",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
                LazyRow {
                    items(trendingMovies) {movie->
                        MovieItem(movie,favoriteViewModel,navController)
                    }
                }
            }
            item {
                Text("Popular Movies",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
                LazyRow {
                    items(popularMovies) { movie ->
                        MovieItem(movie,favoriteViewModel,navController)
                    }
                }
            }

            item {
                Text("Top Rated Movies",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
                LazyRow {
                    items(topRatedMovies) { movie ->
                        MovieItem(movie,favoriteViewModel,navController)
                    }
                }
            }

            item {
                Text("Upcoming Movies",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
                LazyRow {
                    items(upcomingMovies) { movie ->
                        MovieItem(movie,favoriteViewModel,navController)
                    }
                }
            }
        }
    }
}