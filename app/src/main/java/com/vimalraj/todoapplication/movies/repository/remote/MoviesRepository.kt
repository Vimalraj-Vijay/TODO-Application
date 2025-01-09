package com.vimalraj.todoapplication.movies.repository.remote

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.movies.data.MoviesList

interface MoviesRepository {

    suspend fun getMovieList(): ResultHandler<List<MoviesList>>

}