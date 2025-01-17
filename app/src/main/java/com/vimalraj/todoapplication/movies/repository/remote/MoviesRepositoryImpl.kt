package com.vimalraj.todoapplication.movies.repository.remote

import com.vimalraj.network.ResultHandler
import com.vimalraj.network.safeApiCall
import com.vimalraj.todoapplication.application.database.AppDatabase
import com.vimalraj.todoapplication.movies.MovieApiClient
import com.vimalraj.todoapplication.movies.data.MoviesList
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieApiClient: MovieApiClient,
    private val appDatabase: AppDatabase
) : MoviesRepository {

    override suspend fun getMovieList(): ResultHandler<MoviesList> {
        val result = safeApiCall {
            val url = "https://codingdev.free.beeceptor.com/movies"
            movieApiClient.getMoviesStatus(url = url)
        }

        if (result is ResultHandler.Success) {
            appDatabase.moviesDao().insertMovies(result.data)
        }

        return result
    }

    override suspend fun getMovieListFromLocal(): MoviesList? {
        return appDatabase.moviesDao().getMoviesForLocal()
    }


}