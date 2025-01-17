package com.vimalraj.todoapplication.movies.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.vimalraj.todoapplication.application.database.converters.ListConverter

@Entity
data class MoviesList(
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int = 0,
    @SerializedName("statusCode")
    val statusCode: String,
    @SerializedName("movies")
    @TypeConverters(ListConverter::class)
    val movies: List<MovieDetails>
)
