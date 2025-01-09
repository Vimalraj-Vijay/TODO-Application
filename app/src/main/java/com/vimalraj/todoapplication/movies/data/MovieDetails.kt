package com.vimalraj.todoapplication.movies.data

import com.google.gson.annotations.SerializedName


data class MovieDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_url")
    val posterURL: String?,
    @SerializedName("imdb_rating")
    val imdbRating: String?
)
