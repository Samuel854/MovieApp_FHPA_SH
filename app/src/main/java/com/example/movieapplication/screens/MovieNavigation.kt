package com.example.movieapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapplication.screens.DetailScreen
import com.example.movieapplication.screens.FavoriteWindow
import com.example.movieapplication.screens.HomeScreen

@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homescreen"){

        composable("homescreen"){ HomeScreen(navController = navController)}
        composable("favoritescreen"){ FavoriteWindow(navController = navController) }
        composable(
            route ="detailscreen/{movieId}",
        arguments = listOf(navArgument("movieId") {
            type = NavType.StringType
        })
        ) { backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }


    }

}
