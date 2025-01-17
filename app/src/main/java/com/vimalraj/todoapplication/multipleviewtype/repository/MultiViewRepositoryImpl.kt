package com.vimalraj.todoapplication.multipleviewtype.repository

import com.vimalraj.network.ResultHandler
import com.vimalraj.network.safeApiCall
import com.vimalraj.todoapplication.application.database.AppDatabase
import com.vimalraj.todoapplication.multipleviewtype.api.MultiViewClient
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse
import javax.inject.Inject

class MultiViewRepositoryImpl @Inject constructor(
    private val multiViewClient: MultiViewClient,
    private val appDatabase: AppDatabase
) : MultiViewRepository {

    override suspend fun fetchMultipleView(): ResultHandler<MultipleViewsResponse> {
        val result = safeApiCall {
            val url = "https://codingdev.free.beeceptor.com/multiviewtype"
            multiViewClient.fetchMultiViewJson(url = url)
        }

        if (result is ResultHandler.Success) {
            appDatabase.multiViewTypeDao().insertMultipleViews(multipleViewsResponse = result.data)
        }

        return result
    }

    override suspend fun fetchMultipleViewFromLocal(): MultipleViewsResponse? {
        return appDatabase.multiViewTypeDao().getMultipleViewsFromLocal()
    }


}