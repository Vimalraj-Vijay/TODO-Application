package com.vimalraj.todoapplication.movies

import com.vimalraj.todoapplication.movies.data.MoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface MovieApiClient {
    //@GET("cbfdc7c1-1268-4694-b4d3-8434337b8738") // - > Error response 500
    @GET
    suspend fun getMoviesStatus(@Url url: String): Response<MoviesList>

}