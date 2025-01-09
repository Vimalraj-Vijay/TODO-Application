package com.vimalraj.todoapplication.movies.repository.remote

import com.vimalraj.network.ResultHandler
import com.vimalraj.network.safeApiCall
import com.vimalraj.todoapplication.movies.MovieApiClient
import com.vimalraj.todoapplication.movies.data.MoviesList
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieApiClient: MovieApiClient
) : MoviesRepository {

    override suspend fun getMovieList(): ResultHandler<MoviesList> {
        return safeApiCall {
            movieApiClient.getMoviesStatus()
        }
    }


}