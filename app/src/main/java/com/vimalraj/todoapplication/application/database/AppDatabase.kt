package com.vimalraj.todoapplication.application.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vimalraj.todoapplication.application.database.converters.ListConverter
import com.vimalraj.todoapplication.movies.data.MoviesList
import com.vimalraj.todoapplication.movies.repository.local.MoviesDao
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse
import com.vimalraj.todoapplication.multipleviewtype.repository.local.MultiViewTypeDao
import com.vimalraj.todoapplication.todo.database.dao.TodoDao
import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity

@Database(
    version = 5,
    entities = [TodoModelAndEntity::class, MoviesList::class, MultipleViewsResponse::class],
    exportSchema = false
)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    abstract fun moviesDao(): MoviesDao

    abstract fun multiViewTypeDao(): MultiViewTypeDao
}