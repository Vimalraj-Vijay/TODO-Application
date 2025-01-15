package com.vimalraj.todoapplication.multipleviewtype.api

import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface MultiViewClient {

    @GET
    suspend fun fetchMultiViewJson(@Url url: String): Response<MultipleViewsResponse>
}