package com.example.movieapplication.screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapplication.Movie
import com.example.movieapplication.getMovies
import com.example.movieapplication.ui.theme.MovieApplicationTheme
import com.example.movieapplication.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    // Can also add Welcome Screen.  etc.

    var showMenu by remember {
        mutableStateOf(false)
    }
    MovieApplicationTheme() {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Movies") },
                    actions = {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                        }

                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = { navController.navigate(route = "favoritescreen")}) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favorites",
                                        modifier = Modifier.padding(4.dp)
                                    )
                                    Text(
                                        text = "Favorites", modifier = Modifier
                                            .padding(4.dp)
                                            .width(100.dp)
                                    )

                                }

                            }
                        }
                    }
                )
            }
        ) {
            MainContent(navController = navController)
        }
    }

}

@Composable
fun MainContent(movies: List<Movie> = getMovies(), navController: NavController ){
    LazyColumn(){
        items(movies){ movie ->
            MovieRow(movie = movie) { movieId ->
                    navController.navigate(route = "detailscreen/$movieId")

                //onclick funtion can also be used like this. Last argument automatially an onclicked so it does not need to be specified.
            }
        }
    }
}