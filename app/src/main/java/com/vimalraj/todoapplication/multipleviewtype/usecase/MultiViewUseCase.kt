package com.vimalraj.todoapplication.multipleviewtype.usecase

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse

interface MultiViewUseCase {

    suspend fun getMultiViewTypeJson(): ResultHandler<MultipleViewsResponse>
}