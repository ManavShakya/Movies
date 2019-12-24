package com.example.movies.rest

import com.example.movies.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingApiService {
    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") key:String): Call<MovieResponse>
}