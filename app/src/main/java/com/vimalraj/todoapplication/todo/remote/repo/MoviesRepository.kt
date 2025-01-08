package com.vimalraj.todoapplication.todo.remote.repo

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.todo.remote.data.MoviesList

interface MoviesRepository {

    suspend fun getMovieList(): ResultHandler<List<MoviesList>>

}