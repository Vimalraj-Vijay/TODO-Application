package com.vimalraj.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiClient {

    @GET
    suspend fun <T> handleGETMethod(@Url url: String): Response<T>
}