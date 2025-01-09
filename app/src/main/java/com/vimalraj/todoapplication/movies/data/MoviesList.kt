package com.vimalraj.todoapplication.movies.data

import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("movies")
    var movies: ArrayList<Movies> = arrayListOf()
)
