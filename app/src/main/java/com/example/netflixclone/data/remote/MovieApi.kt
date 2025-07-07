package com.example.netflixclone.data.remote

import com.example.netflixclone.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieResponse
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieResponse
}
