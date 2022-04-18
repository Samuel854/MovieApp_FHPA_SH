package com.example.movieapplication.KotlinClass


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movieapplication.Movie

class FavoritesViewModel : ViewModel() {
    private val _favoriteMovies = mutableStateListOf<Movie>()
    val favoriteMovies: List <Movie>
        get() = _favoriteMovies

    //logic.../ add different Functions.

    fun addMovieToFavorites( movie: Movie ) : Boolean {
        return if (!isFavorite( movie = movie )) {
            this._favoriteMovies.add( movie )
            true
        } else {
            false
        }
    }

    fun removeMovie(movie: Movie){
        _favoriteMovies.remove(movie)
    }

    fun isFavorite(movie: Movie) : Boolean {
        return _favoriteMovies.any {m -> m.id == movie.id}

    }

    fun getAllMovies(): List<Movie>{
        return _favoriteMovies
    }

    fun sortMovies(movie: Movie){}



}