package com.vimalraj.todoapplication.movies.usecase

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.movies.data.MoviesList
import com.vimalraj.todoapplication.movies.repository.remote.MoviesRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : MovieUseCase {

    override suspend fun fetchMovieListResponse(): ResultHandler<MoviesList> {
        val fetchFromLocal = moviesRepository.getMovieListFromLocal()
        return if (fetchFromLocal != null) {
            ResultHandler.Success(data = fetchFromLocal)
        } else {
            moviesRepository.getMovieList()
        }
    }
}