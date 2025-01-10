package com.vimalraj.todoapplication.movies

import com.vimalraj.todoapplication.movies.data.MoviesList
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiClient {
    //@GET("cbfdc7c1-1268-4694-b4d3-8434337b8738") // - > Error response 500
    @GET("43c58e72-d161-422a-ba29-28762354b352") //- > For Valid 200 Response
    suspend fun getMoviesStatus(): Response<MoviesList>

}