package com.vimalraj.todoapplication.learning.viewmodel

import com.vimalraj.coremodule.BaseEvents
import com.vimalraj.coremodule.BaseViewModel
import com.vimalraj.coremodule.BaseViewState
import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.learning.usecase.LearnUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor(
    private val learnUseCase: LearnUseCase
) : BaseViewModel<BaseViewState, BaseEvents>() {

    override val initialState: BaseViewState?
        get() = null

    suspend fun executeAllBFF() {
        val jokeFlow = learnUseCase.getJoke()

        jokeFlow.collect {
            when (it) {
                is ResultHandler.Success -> {
                    println("collect only Vowels ${it.data.joke}")
                }

                is ResultHandler.AccessDenied -> {
                    println("AccessDenied")
                }

                else -> {

                }
            }
        }
    }

}