package com.vimalraj.todoapplication.movies.viewmodel

import androidx.lifecycle.viewModelScope
import com.vimalraj.coremodule.BaseViewModel
import com.vimalraj.coremodule.HandleEvent
import com.vimalraj.network.RemoteApiError
import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.movies.data.MoviesList
import com.vimalraj.todoapplication.movies.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : BaseViewModel<MovieViewState, MovieViewEvents>() {

    override val initialState: MovieViewState
        get() = MovieViewState(isLoading = true)

    fun executeSuspend() {
        viewModelScope.launch {
            when (val result = movieUseCase.fetchMovieListResponse()) {
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
        if (RemoteApiError.NO_INTERNET == remoteApiError) {
            // Handle NO_INTERNET Event
            mutableStateFlow.update { currentState ->
                currentState?.copy(
                    isError = false,
                    isLoading = false,
                    movieDetailsItem = emptyList()
                )
            }
            mutableEventFlow.value = HandleEvent(MovieViewEvents.LaunchNoInternetConnection)
        } else {
            mutableStateFlow.update { currentState ->
                currentState?.copy(isError = true, isLoading = false)
            }
        }
    }

    private fun handleSuccess(data: MoviesList) {
        mutableStateFlow.update { currentState ->
            currentState?.copy(
                movieDetailsItem = data.movies,
                isLoading = false
            )
        }
    }
}