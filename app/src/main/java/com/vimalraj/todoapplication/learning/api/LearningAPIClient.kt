package com.vimalraj.todoapplication.learning.api

import com.vimalraj.todoapplication.learning.model.Facts
import com.vimalraj.todoapplication.learning.model.Joke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface LearningAPIClient {

    @GET
    suspend fun getAnyJoke(@Url url: String): Response<Joke>

    @GET
    suspend fun getAnyFacts(@Url url: String): Response<Facts>

}