package com.vimalraj.todoapplication.todo.remote.usecase

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.todo.remote.data.MoviesList
import com.vimalraj.todoapplication.todo.remote.repo.MoviesRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : MovieUseCase {

    override suspend fun getMovies(): ResultHandler<List<MoviesList>> {
        return moviesRepository.getMovieList()
    }
}