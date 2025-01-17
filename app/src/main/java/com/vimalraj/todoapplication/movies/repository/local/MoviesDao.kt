package com.vimalraj.todoapplication.movies.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vimalraj.todoapplication.movies.data.MoviesList

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesList: MoviesList)

    @Query("SELECT * FROM MoviesList")
    suspend fun getMoviesForLocal(): MoviesList?
}