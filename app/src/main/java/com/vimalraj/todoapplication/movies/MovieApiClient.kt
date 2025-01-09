package com.vimalraj.todoapplication.movies

import com.vimalraj.todoapplication.movies.data.MoviesList
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiClient {
    @GET("43c58e72-d161-422a-ba29-28762354b352")
    suspend fun getMoviesStatus(): Response<MoviesList>

}