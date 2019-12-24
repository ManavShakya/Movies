package com.example.movies.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<Movie>,
    @SerializedName("total_results") var totalResults: Int,
    @SerializedName("total_pages") var totalPages: Int)

