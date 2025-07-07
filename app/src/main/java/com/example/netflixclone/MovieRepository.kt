package com.example.netflixclone

import com.example.netflixclone.data.remote.RetrofitInstance
import com.example.netflixclone.data.model.Movie

class MovieRepository {
    private val api = RetrofitInstance.api
    private val apiKey = "2edf58670bce14ce6fcc2f28b0198e06"

    suspend fun getTrending(): List<Movie> {
        val response = api.getTrendingMovies(apiKey)
        println("Trending response: $response")
        return response.results
    }
    suspend fun getPopular(): List<Movie> {
        val response = api.getPopularMovies(apiKey)
        println("Popular response: $response")
            return response.results
    }
    suspend fun getTopRated(): List<Movie> = api.getTopRatedMovies(apiKey).results
    suspend fun getUpcoming(): List<Movie> = api.getUpcomingMovies(apiKey).results
    suspend fun searchMovies(query: String): List<Movie> {
        val response = api.searchMovies(apiKey, query)
        println("Search response: $response")
        return response.results
    }
}

