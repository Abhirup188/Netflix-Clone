package com.example.netflixclone

sealed class Screen(val route:String) {
    object SignUpScreen : Screen("sign_up_screen")
    object HomeScreen : Screen("home_screen")
    object LoginScreen : Screen("login_screen")
    object SearchScreen : Screen("search_screen")
    object WatchListScreen : Screen("watch_list_screen")
    object ProfileScreen : Screen("profile_screen")
    object DetailScreen : Screen("detail_screen/{movieId}"){
        fun createRoute(movieId:Int) = "detail_screen/$movieId"
    }
}