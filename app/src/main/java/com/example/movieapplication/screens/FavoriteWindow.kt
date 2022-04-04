package com.example.movieapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapplication.Movie
import com.example.movieapplication.getFavoriteMovies
import com.example.movieapplication.getMovies
import com.example.movieapplication.ui.theme.MovieApplicationTheme
import com.example.movieapplication.widgets.MovieRow


@Composable
fun FavoriteWindow(navController: NavController = rememberNavController()){
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
        MainContent2(navController = navController)
    }

}

@Composable
fun MainContent2(movies: List<Movie> = getFavoriteMovies(),navController: NavController ){

    LazyColumn(){
        items(movies){ movie ->
            MovieRow(movie = movie){ movieId ->
                navController.navigate(route = "detailscreen/$movieId")

                //onclick funtion can also be used like this. Last argument automatially an onclicked so it does not need to be specified.
            }
        }
    }


}