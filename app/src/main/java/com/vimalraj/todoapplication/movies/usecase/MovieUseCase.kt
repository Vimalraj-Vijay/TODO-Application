package com.vimalraj.todoapplication.movies.usecase

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.movies.data.MoviesList

interface MovieUseCase {

    suspend fun fetchMovieListResponse(): ResultHandler<MoviesList>

}