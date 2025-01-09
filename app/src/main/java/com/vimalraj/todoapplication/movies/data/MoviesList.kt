package com.vimalraj.todoapplication.movies.data

import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("statusCode")
    val statusCode: String,
    @SerializedName("movies")
    val movies: List<MovieDetails>
)
