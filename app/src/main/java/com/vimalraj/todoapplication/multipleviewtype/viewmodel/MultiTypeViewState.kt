package com.vimalraj.todoapplication.multipleviewtype.viewmodel

import com.vimalraj.coremodule.BaseViewState
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse

data class MultiTypeViewState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val multipleViewsResponse: MultipleViewsResponse? = null
) : BaseViewState
