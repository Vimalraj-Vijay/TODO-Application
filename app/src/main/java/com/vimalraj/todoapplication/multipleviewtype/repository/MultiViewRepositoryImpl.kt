package com.vimalraj.todoapplication.multipleviewtype.repository

import com.vimalraj.network.ResultHandler
import com.vimalraj.network.safeApiCall
import com.vimalraj.todoapplication.multipleviewtype.api.MultiViewClient
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse
import javax.inject.Inject

class MultiViewRepositoryImpl @Inject constructor(
    private val multiViewClient: MultiViewClient
) : MultiViewRepository {

    override suspend fun fetchMultipleView(): ResultHandler<MultipleViewsResponse> {
        return safeApiCall {
            val url = "https://codingdev.free.beeceptor.com/multiviewtype"
            multiViewClient.fetchMultiViewJson(url = url)
        }
    }


}