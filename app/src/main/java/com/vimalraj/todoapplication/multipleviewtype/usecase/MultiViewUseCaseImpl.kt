package com.vimalraj.todoapplication.multipleviewtype.usecase

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse
import com.vimalraj.todoapplication.multipleviewtype.repository.MultiViewRepository
import javax.inject.Inject

class MultiViewUseCaseImpl @Inject constructor(
    private val multiViewRepository: MultiViewRepository
) : MultiViewUseCase {

    override suspend fun fetchMultiViewTypeResponse(): ResultHandler<MultipleViewsResponse> {
        val fetchFromLocal = multiViewRepository.fetchMultipleViewFromLocal()
        return if (fetchFromLocal != null) {
            ResultHandler.Success(data = fetchFromLocal)
        } else {
            multiViewRepository.fetchMultipleView()
        }
    }
}