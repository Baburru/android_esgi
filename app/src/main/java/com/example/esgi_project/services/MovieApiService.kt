package com.example.esgi_project.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "a538a7b13f813755f0e7cfc71e36677d"

data class MovieResponse(val results: List<MovieItem>)
data class MovieItem(val id: Int, val title: String, val overview: String, val poster_path: String, val vote_average: Float)

interface MovieApiService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "fr-FR",
        @Query("page") page: Int = 1
    ): MovieResponse

    companion object {
        fun create(): MovieApiService {
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApiService::class.java)
        }
    }
}
