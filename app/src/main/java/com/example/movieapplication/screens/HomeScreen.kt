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
import com.example.movieapplication.KotlinClass.FavoritesViewModel
import com.example.movieapplication.Movie
import com.example.movieapplication.getMovies
import com.example.movieapplication.ui.theme.MovieApplicationTheme
import com.example.movieapplication.widgets.MovieRow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapplication.widgets.FavoriteIcon

@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewModel: FavoritesViewModel){
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
            MainContent(getMovies(),navController = navController,favViewModel = viewModel)
        }
    }

}

@Composable
fun MainContent(movieList : List<Movie>, navController: NavController, favViewModel: FavoritesViewModel) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        LazyColumn {
            items( movieList ) { movie ->
                MovieRow(
                    movie = movie,
                    showFavoriteIcon = true,
                    content = {
                        FavoriteIcon(
                            movie = movie,
                            isFavorite = favViewModel.isFavorite(movie = movie)
                        ) { movie ->
                            if ( ! favViewModel.addMovieToFavorites( movie = movie ) ) favViewModel.removeMovie( movie = movie )
                        } }
                ) { movieId -> navController.navigate("detailscreen/$movieId") }
            }
        }
    }
}
