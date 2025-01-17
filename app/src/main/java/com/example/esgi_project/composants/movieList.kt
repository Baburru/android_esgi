package com.example.esgi_project.composants

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.esgi_project.viewmodel.MovieViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun MovieList() {

    // Correctly retrieve the ViewModel
    val movieViewModel: MovieViewModel = viewModel()

    // Collect the movies from the ViewModel state
    val movies by movieViewModel.movies.collectAsState()

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(movies.size) { index ->
            Movie(movie = movies[index])
        }
    }
}

