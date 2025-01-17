package com.example.esgi_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esgi_project.api.MovieApiService
import com.example.esgi_project.api.MovieItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<MovieItem>>(emptyList())
    val movies: StateFlow<List<MovieItem>> get() = _movies

    init {
        fetchTopRatedMovies()
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            val movieApi = MovieApiService.create()
            val response = movieApi.getTopRatedMovies()
            _movies.value = response.results.take(20)
        }
    }
}
