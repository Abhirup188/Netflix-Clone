package com.example.netflixclone

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.netflixclone.Authentication.AuthViewModel
import com.example.netflixclone.Authentication.LoginScreen
import com.example.netflixclone.Authentication.SignUpScreen
import com.example.netflixclone.data.room.FavoriteViewModel
import com.example.netflixclone.ui.screens.ProfileScreen

@Composable
fun Navigation(navController: NavHostController,authViewModel: AuthViewModel,
               mainViewModel: MainViewModel,
               favouriteViewModel: FavoriteViewModel
){
    NavHost(navController = navController,
        startDestination = Screen.LoginScreen.route
    ){
        composable(Screen.SignUpScreen.route){
            SignUpScreen(navController,
                onNavigationToLogInPage = {
                    navController.navigate(Screen.LoginScreen.route)},authViewModel = authViewModel
                )
            }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigationToSignInPage =
                    {
                        navController.navigate(Screen.SignUpScreen.route)
                    },
                authViewModel,navController
            )
        }
        composable(Screen.HomeScreen.route) {
            Scaffold(
                bottomBar = {
                    BottomNavBar(navController)
                }
            ) {
                HomeScreen(mainViewModel,authViewModel,navController,favouriteViewModel)
            }
        }
        composable(Screen.SearchScreen.route){
            Scaffold(
                bottomBar = {
                    BottomNavBar(navController)
                }
            ) {
                SearchScreen(mainViewModel,favouriteViewModel,navController)
            }
        }
        composable(Screen.WatchListScreen.route) {
            Scaffold(
                bottomBar = {
                    BottomNavBar(navController)
                }
            ) {
                WatchListScreen(favouriteViewModel,navController)
            }
        }
        composable(Screen.ProfileScreen.route) {
            Scaffold(
                bottomBar = {
                    BottomNavBar(navController)
                }
            ){
                ProfileScreen(
                    onSignOut = { navController.navigate(Screen.LoginScreen.route) },
                    authViewModel
                )
            }
        }
        composable(Screen.DetailScreen.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            )
        ){
            val movieId = it.arguments?.getInt("movieId")?:-1
            DetailScreen(movieId,mainViewModel,favouriteViewModel)
        }
    }
}
