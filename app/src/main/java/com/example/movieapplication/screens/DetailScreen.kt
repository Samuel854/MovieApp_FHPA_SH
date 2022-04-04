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
import com.example.movieapplication.Movie
import com.example.movieapplication.getMovies
import com.example.movieapplication.ui.theme.MovieApplicationTheme
import com.example.movieapplication.widgets.ImageGallery
import com.example.movieapplication.widgets.MovieRow

@Preview
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = "tt0944947"
    ){

    val movie = filterMovie(movieId = movieId)

    MainContent(movie.title, navController = navController) {
        
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column {
                MovieRow(movie = movie)

                Spacer(modifier = Modifier.height(8.dp))

                Divider()

                ImageGallery(movie = movie)


            }
            
        }

        //Text(text = "${movie.title}",fontSize = 20.sp, style = MaterialTheme.typography.h5)
        //Text(text = "My DetailScreen! $movieId")
    }
}

@Composable
fun MainContent(movieTitle :String, navController: NavController = rememberNavController(), content: @Composable () -> Unit){

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
                Text(text = movieTitle, fontSize = 20.sp)

            }

        }

        }
    ){
        content()
    }


}

@Composable
fun filterMovie(movieId: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}

