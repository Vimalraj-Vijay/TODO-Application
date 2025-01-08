package com.vimalraj.todoapplication.todo.remote.repo

import com.vimalraj.network.APIExecutor
import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.todo.remote.data.MoviesList
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiExecutor: APIExecutor
) : MoviesRepository {

    override suspend fun getMovieList(): ResultHandler<List<MoviesList>> {
        // Another Mock API Response
        // https://mocki.io/v1/a2c5c6c5-cd2e-4eb5-a078-82c649af14e6
        return apiExecutor.executeGETCall(url = "https://run.mocky.io/v3/bcfe3f67-8115-4b27-91c7-eee598b1c79b")
    }


}