package com.example.netflixclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.netflixclone.Authentication.AuthViewModel
import com.example.netflixclone.data.room.AppDatabase
import com.example.netflixclone.data.room.FavoriteRepository
import com.example.netflixclone.data.room.FavoriteViewModel
import com.example.netflixclone.data.room.FavoriteViewModelFactory
import com.example.netflixclone.ui.theme.NetflixCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            println("Global Crash: ${throwable.localizedMessage}")
        }
        setContent {
            val movieRepo = MovieRepository()
            val viewModel: MainViewModel = ViewModelProvider(
                this,
                MainViewModelFactory(movieRepo),
            )[MainViewModel::class.java]
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()
            val context = LocalContext.current
            val db = AppDatabase.getDatabase(context)
            val dao = db.favoriteDao()
            val repo = FavoriteRepository(dao)
            val favouriteViewModel: FavoriteViewModel = ViewModelProvider(
                this,
                FavoriteViewModelFactory(repo)
            )[FavoriteViewModel::class.java]
            NetflixCloneTheme {
                Navigation(navController,authViewModel,viewModel,favouriteViewModel)
            }
        }
    }
}