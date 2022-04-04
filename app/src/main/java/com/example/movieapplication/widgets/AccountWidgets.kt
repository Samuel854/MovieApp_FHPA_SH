package com.example.movieapplication.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.movieapplication.Movie
import com.example.movieapplication.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick : (String) -> Unit = {}) { //Über Refactor.
    Card(
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            //.height(130.dp)
            .animateContentSize()//Changes Card size with added Text.
            .clickable { onItemClick(movie.id) },
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp),
                elevation = 6.dp,
                shape = RoundedCornerShape(corner = CornerSize(50.dp))
            ) {
                //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie pic")
                Image(painter = rememberImagePainter(
                    data = movie.images[0],
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                    contentDescription = "Movie Thumbnail",
                    modifier = Modifier.size(128.dp),

                )
            }
            Column {
                Text(text = "")
                Text(text = movie.title, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Text(text = "Director: ${movie.director}")
                Text(text = "Year: ${movie.year}")
                var visible by remember { mutableStateOf(false) }
                AnimatedVisibility(visible = visible) {

                    Column {
                        Text(text = "")
                        Text(text = "Plot: ${movie.plot}")
                        Text(text = "")
                        Divider()
                        Text(text = "")
                        Text(text = "Genre: ${movie.genre}")
                        Text(text = "Actors: ${movie.actors}")
                        Text(text = "Rating: ${movie.rating}")
                    }
                }
                IconButton(onClick = { visible= !visible }) {
                    if (visible){
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Favorites")
                    }else{
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Favorites")
                    }
                }
            }
        }
    }
}

@Composable
fun ImageGallery(movie: Movie = getMovies()[0]){
    LazyRow(){
        items(movie.images){ image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "Movie Image")

        }

        }

    }


}