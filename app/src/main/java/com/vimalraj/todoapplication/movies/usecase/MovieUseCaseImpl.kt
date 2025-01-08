package com.vimalraj.todoapplication.movies.usecase

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.movies.data.MoviesList
import com.vimalraj.todoapplication.movies.repository.remote.MoviesRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : MovieUseCase {

    override suspend fun getMovies(): ResultHandler<List<MoviesList>> {
        return moviesRepository.getMovieList()
    }
}