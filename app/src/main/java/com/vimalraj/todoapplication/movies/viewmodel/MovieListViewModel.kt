package com.vimalraj.todoapplication.movies.viewmodel

import androidx.lifecycle.viewModelScope
import com.vimalraj.coremodule.BaseEvents
import com.vimalraj.coremodule.BaseViewModel
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
) : BaseViewModel<MovieViewState, BaseEvents>() {

    override val initialState: MovieViewState
        get() = MovieViewState()


    fun fetchMovieListFromRemote() {
        mutableStateFlow.update { currentState ->
            currentState?.copy(isLoading = true)
        }
        executeSuspend {
            movieUseCase.getMovies()
        }
    }

    private fun <T> executeSuspend(block: suspend () -> ResultHandler<T>) {
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
        if (RemoteApiError.NO_INTERNET == remoteApiError) {
            // Handle NO_INTERNET Event
            println("Logging -> NO_INTERNET")
        }
        mutableStateFlow.update { currentState ->
            currentState?.copy(isError = true, isLoading = false)
        }
    }

    private fun <T> handleSuccess(data: T) {
        mutableStateFlow.update { currentState ->
            when (data) {
                is MoviesList -> currentState?.copy(
                    movieDetailsItem = data.movies,
                    isLoading = false
                )

                else -> currentState?.copy(isError = false, movieDetailsItem = emptyList())
            }
        }
    }
}