package com.vimalraj.todoapplication.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class TypeBasedViewModel<VIEW_STATE : BaseViewState, VIEW_EVENTS : BaseEvents> :
    ViewModel() {
    protected val mutableViewState: MutableLiveData<VIEW_STATE> = MutableLiveData()
    protected val mutableViewEvents: MutableLiveData<HandleEvent<VIEW_EVENTS>> = MutableLiveData()

    val viewState: LiveData<VIEW_STATE> = mutableViewState
    val viewEvents: LiveData<HandleEvent<VIEW_EVENTS>> = mutableViewEvents
}