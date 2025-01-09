package com.vimalraj.todoapplication.movies.viewmodel

import androidx.lifecycle.viewModelScope
import com.vimalraj.coremodule.BaseEvents
import com.vimalraj.coremodule.BaseViewModel
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
        executeSuspend {
            movieUseCase.getMovies()
        }
    }

    private fun <T> executeSuspend(block: suspend () -> ResultHandler<T>) {
        viewModelScope.launch {
            when (val result = block()) {
                is ResultHandler.Success -> handleSuccess(result.data)
                is ResultHandler.Error -> {

                }

                else -> {

                }
            }
        }
    }

    private fun <T> handleSuccess(data: T) {
        mutableStateFlow.update { currentState ->
            when (data) {
                is MoviesList -> currentState?.copy(movieDetailsItem = data.movies)
                else -> currentState?.copy(isError = false, movieDetailsItem = emptyList())
            }
        }
    }
}