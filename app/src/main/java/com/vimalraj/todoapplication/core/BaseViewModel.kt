package com.vimalraj.todoapplication.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<VIEW_STATE : BaseViewState?, VIEW_EVENTS : BaseEvents?> : ViewModel() {

    abstract val initialState: VIEW_STATE?

    protected val mutableStateFlow: MutableStateFlow<VIEW_STATE?> by lazy {
        MutableStateFlow(initialState)
    }
    protected val mutableEventFlow: MutableStateFlow<HandleEvent<VIEW_EVENTS?>> by lazy {
        MutableStateFlow(HandleEvent())
    }

    val viewState: StateFlow<VIEW_STATE?> = mutableStateFlow
    val viewEvent: StateFlow<HandleEvent<VIEW_EVENTS?>> = mutableEventFlow
}