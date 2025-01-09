package com.vimalraj.todoapplication.movies.viewmodel

import com.vimalraj.coremodule.BaseViewState
import com.vimalraj.todoapplication.movies.data.MovieDetails

data class MovieViewState(
    val movieDetailsItem: List<MovieDetails> = emptyList(),
    val isError: Boolean = false
) : BaseViewState
