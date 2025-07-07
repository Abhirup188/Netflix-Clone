package com.example.netflixclone

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(containerColor = Color.Black) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White) },
            label = { Text("Home", color = Color.White) },
            selected = currentRoute == Screen.HomeScreen.route,
            onClick = {
                if (currentRoute != Screen.HomeScreen.route) {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Search",tint = Color.White) },
            label = { Text("Search",color = Color.White) },
            selected = currentRoute == Screen.SearchScreen.route,
            onClick = {
                if (currentRoute != Screen.SearchScreen.route) {
                navController.navigate(Screen.SearchScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            } }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Watchlist",tint = Color.White) },
            label = { Text("Watchlist",color = Color.White) },
            selected = currentRoute == Screen.WatchListScreen.route,
            onClick = {
                if (currentRoute != Screen.WatchListScreen.route) {
                    navController.navigate(Screen.WatchListScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile",tint = Color.White) },
            label = { Text("Profile",color = Color.White) },
            selected = currentRoute == Screen.ProfileScreen.route,
            onClick = {
                if (currentRoute != Screen.ProfileScreen.route) {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            } }
        )
    }
}
