package com.example.movies.rest

import com.example.movies.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TopApiService {
    @GET("movie/top_rated")
    fun getTopMovies(@Query("api_key") key: String): Call<MovieResponse>
}