package com.vimalraj.todoapplication.multipleviewtype.repository

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse

interface MultiViewRepository {

    suspend fun fetchMultipleView(): ResultHandler<MultipleViewsResponse>
}