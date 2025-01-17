package com.vimalraj.todoapplication.application.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vimalraj.todoapplication.movies.data.MovieDetails
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViews

class ListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMovieList(list: List<MovieDetails>?): String? {
        if (list == null) {
            return null
        }
        val type = object : TypeToken<List<MovieDetails>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toMovieList(json: String?): List<MovieDetails>? {
        if (json == null) {
            return null
        }
        val type = object : TypeToken<List<MovieDetails>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromMultipleViewsList(list: List<MultipleViews>?): String? {
        if (list == null) {
            return null
        }
        val type = object : TypeToken<List<MultipleViews>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toMultipleViewsList(json: String?): List<MultipleViews>? {
        if (json == null) {
            return null
        }
        val type = object : TypeToken<List<MultipleViews>>() {}.type
        return gson.fromJson(json, type)
    }
}