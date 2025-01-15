package com.vimalraj.todoapplication.multipleviewtype.viewmodel

import androidx.lifecycle.viewModelScope
import com.vimalraj.coremodule.BaseViewModel
import com.vimalraj.coremodule.HandleEvent
import com.vimalraj.network.RemoteApiError
import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse
import com.vimalraj.todoapplication.multipleviewtype.usecase.MultiViewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MultiTypeViewModel @Inject constructor(
    private val multiViewUseCase: MultiViewUseCase
) : BaseViewModel<MultiTypeViewState, MultiTypeViewEvents>() {


    override val initialState: MultiTypeViewState
        get() = MultiTypeViewState(multipleViewsResponse = null, isLoading = true)


    fun fetchMultiViewTypeJson() {
        executeSuspend {
            multiViewUseCase.getMultiViewTypeJson()
        }
    }

    private fun executeSuspend(block: suspend () -> ResultHandler<MultipleViewsResponse>) {
        viewModelScope.launch {
            when (val result = block()) {
                is ResultHandler.Success -> handleSuccess(result.data)
                is ResultHandler.Error -> {
                    handleError(result.remoteApiError)
                }

                is ResultHandler.AccessDenied -> {
                    // Do nothing
                }
            }
        }
    }

    private fun handleError(remoteApiError: RemoteApiError) {
        if (remoteApiError == RemoteApiError.NO_INTERNET) {
            mutableEventFlow.value = HandleEvent(MultiTypeViewEvents.LaunchNoInternetConnection)
        } else {
            mutableStateFlow.update { currentState ->
                currentState?.copy(
                    isError = true, isLoading = false
                )
            }
        }

    }

    private fun handleSuccess(data: MultipleViewsResponse) {
        println(data)
        mutableStateFlow.update { currentState ->
            currentState?.copy(
                isError = false, isLoading = false, multipleViewsResponse = data
            )
        }
    }
}