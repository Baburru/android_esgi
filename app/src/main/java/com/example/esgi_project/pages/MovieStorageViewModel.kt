package com.example.esgi_project.pages

import MovieStorage
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieStorageViewModel(
    private val movieStorage: MovieStorage
) :ViewModel() {
    val savedMovies = movieStorage.savedMovies

    fun toggleMovie(movieId: String) {
        viewModelScope.launch {
            movieStorage.toggleMovie(movieId)
        }
    }
}