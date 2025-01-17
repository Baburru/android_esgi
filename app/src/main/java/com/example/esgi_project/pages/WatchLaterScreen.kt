package com.example.esgi_project.pages

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.esgi_project.composants.SavedMovie
import com.example.esgi_project.viewmodel.MovieViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchLaterScreen(
    navController: NavController,
    movieStorageModel: MovieStorageViewModel = koinViewModel(),
    movieViewModel: MovieViewModel = koinViewModel()
) {
    val savedMovies by movieStorageModel.savedMovies.collectAsState(initial = emptySet())
    val movies by movieViewModel.movies.collectAsState(initial = emptyList())

    // Filter saved movies based on `savedMovies` IDs
    val savedMoviesList = movies.filter { movie ->
        savedMovies.contains(movie.id.toString())
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = { Text("Regarder plus tard") },
            navigationIcon = {
                IconButton(onClick = {navController.navigateUp()}) {
                    Icon(Icons.Filled.ArrowBackIosNew, contentDescription = "watch later icon")
                }
            }
        )}
    )  { innerPadding ->
            if (savedMoviesList.isEmpty()) {
                // Empty state message
                Box(
                    modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No saved movies.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(savedMoviesList.size, key = { index -> savedMoviesList[index].id } ) { index ->
                        val m = savedMoviesList[index]
                        SavedMovie (
                            movie = m,
                            onRemove = { movieStorageModel.toggleMovie(m.id.toString()) }
                        )
                    }
                }
            }

    }
}
