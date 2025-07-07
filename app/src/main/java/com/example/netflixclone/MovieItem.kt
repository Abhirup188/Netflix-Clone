package com.example.netflixclone

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.netflixclone.data.model.Movie
import com.example.netflixclone.data.room.FavoriteViewModel

@Composable
fun MovieItem(movie: Movie,favoriteViewModel: FavoriteViewModel,navController: NavController) {
    val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
    val isActuallyFavorite = favoriteViewModel.isFavorite(movie)
    var isFavourite by remember { mutableStateOf(isActuallyFavorite) }
    LaunchedEffect(isActuallyFavorite) {
        isFavourite = isActuallyFavorite
    }

    Box(
        modifier = Modifier
            .width(140.dp)
            .padding(8.dp)
    ){
        Column(
            modifier = Modifier
                .width(140.dp)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clickable{
                        Log.d("MovieItem", "Clicked movie ${movie.id}")
                        navController.navigate(Screen.DetailScreen.createRoute(movie.id))
                    },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = movie.title,
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(onClick =
            {
                favoriteViewModel.toggleFavorite(movie)
                isFavourite=!isFavourite
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
        ) {
            Icon(imageVector = if(isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favourite",
                tint = if(isFavourite) Color.Red else Color.White
            )
        }
    }
}
