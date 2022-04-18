package com.example.movieapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapplication.KotlinClass.FavoritesViewModel
import com.example.movieapplication.Movie
import com.example.movieapplication.widgets.MovieRow



@Composable
fun FavoriteWindow(navController: NavController = rememberNavController(),viewModel: FavoritesViewModel){
    // Can also add Welcome Screen.  etc.

    Scaffold(
        topBar =
        { TopAppBar(elevation = 3.dp) {
            Row() {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back.",
                    modifier = Modifier.clickable {
                        navController.popBackStack() //go back to last screen
                    })
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Favorite", fontSize = 20.sp)

            }

        }

        }
    ){
        MainContent(navController = navController, viewModel.favoriteMovies)
    }

}

@Composable
fun MainContent(navController: NavController = rememberNavController(), favMovies: List<Movie>) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        LazyColumn {
            items( favMovies) { movie ->
                MovieRow(movie = movie, showFavoriteIcon = false) { movieId ->
                    navController.navigate("favoritescreen/$movieId")
                }
            }
        }
    }
}



