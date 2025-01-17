package com.example.esgi_project.composants

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.esgi_project.pages.MovieStorageViewModel
import com.example.esgi_project.viewmodel.MovieViewModel
import org.koin.androidx.compose.koinViewModel
import Movie


@Composable
fun MovieList(movieStorageModel: MovieStorageViewModel = koinViewModel(), movieViewModel: MovieViewModel = koinViewModel()) {
    // Collect the movies from the ViewModel state
    val movies by movieViewModel.movies.collectAsState()
    val savedMovies by movieStorageModel.savedMovies.collectAsState(initial = emptySet())

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(movies.size, key = { index -> movies[index].id}) { index ->
            val movie = movies[index]
            val isSaved = savedMovies.contains(movie.id.toString())
            Movie(movie,
                isSaved,
                onToggleSave = { movieId -> movieStorageModel.toggleMovie(movieId) }
            )
        }
    }
}

