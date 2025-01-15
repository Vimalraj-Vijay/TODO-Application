package com.vimalraj.todoapplication.multipleviewtype.usecase

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse
import com.vimalraj.todoapplication.multipleviewtype.repository.MultiViewRepository
import javax.inject.Inject

class MultiViewUseCaseImpl @Inject constructor(
    private val multiViewRepository: MultiViewRepository
) : MultiViewUseCase {


    override suspend fun getMultiViewTypeJson(): ResultHandler<MultipleViewsResponse> {
        return multiViewRepository.fetchMultipleView()
    }
}