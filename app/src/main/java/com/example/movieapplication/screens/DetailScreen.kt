package com.example.movieapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapplication.KotlinClass.FavoritesViewModel
import com.example.movieapplication.Movie
import com.example.movieapplication.getMovieById
import com.example.movieapplication.getMovies
import com.example.movieapplication.ui.theme.MovieApplicationTheme
import com.example.movieapplication.widgets.FavoriteIcon
import com.example.movieapplication.widgets.ImageGallery
import com.example.movieapplication.widgets.MovieRow


@Composable
fun MainContent(
    movie : Movie, favViewModel : FavoritesViewModel  ){

        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column {
                MovieRow(
                    movie = movie,
                    showFavoriteIcon = true,
                    content = {
                        FavoriteIcon(movie = movie, isFavorite = favViewModel.isFavorite(movie = movie)) {
                                movie ->
                            if ( ! favViewModel.addMovieToFavorites( movie = movie ) ) favViewModel.removeMovie( movie = movie )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))

                ImageGallery(movie = movie)


            }

        }

        //Text(text = "${movie.title}",fontSize = 20.sp, style = MaterialTheme.typography.h5)
        //Text(text = "My DetailScreen! $movieId")
    }


@Composable
fun DetailScreen(navController: NavController = rememberNavController(), favViewModel : FavoritesViewModel, movieId : String? = getMovies()[0].id) {
    val movie = getMovieById( movieId = movieId )
    Scaffold(topBar = {
        TopAppBar() {
            Row {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.navigateUp()
                        //navController.popBackStack()
                    }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = movie.title)
            }
        }
    }) {
        MainContent(movie = movie, favViewModel = favViewModel)
    }
}



