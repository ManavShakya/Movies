package com.example.movies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(@SerializedName("backdrop_path") val backdropPath: String,
                   @SerializedName("popularity") val popularity: Double,
                   @SerializedName("vote_count") val voteCount: Int,
                   @SerializedName("video") val video: Boolean,
                   @SerializedName("genre_ids") val genreIds: List<Int>,
                   @SerializedName("id") val id: Int,
                   @SerializedName("original_title") val originalTitle: String,
                   @SerializedName("original_language") val originalLanguage: String,
                   @SerializedName("poster_path") val posterPath: String,
                   @SerializedName("adult") val adult: Boolean,
                   @SerializedName("overview") val overview: String,
                   @SerializedName("release_date") val releaseDate: String,
                   @SerializedName("title") val title: String,
                   @SerializedName("vote_average") val voteAverage: Double): Parcelable