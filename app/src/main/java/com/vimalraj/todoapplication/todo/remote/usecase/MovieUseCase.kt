package com.vimalraj.todoapplication.todo.remote.usecase

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.todo.remote.data.MoviesList

interface MovieUseCase {

    suspend fun getMovies(): ResultHandler<List<MoviesList>>
}