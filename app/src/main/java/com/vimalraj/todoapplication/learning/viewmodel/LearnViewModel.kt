package com.vimalraj.todoapplication.learning.viewmodel

import com.vimalraj.coremodule.BaseEvents
import com.vimalraj.coremodule.BaseViewModel
import com.vimalraj.coremodule.BaseViewState
import com.vimalraj.todoapplication.learning.ProblemSet
import com.vimalraj.todoapplication.learning.usecase.LearnUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor(
    private val learnUseCase: LearnUseCase
) : BaseViewModel<BaseViewState, BaseEvents>() {

    init {
        val result = ProblemSet
        println("LearnViewModel")
        println("twoSum ${result.twoSum(intArrayOf(5, 2, 3, 4, 1), 8)}")
        println("isPalindrome ${result.isPalindrome(223)}")
        println("largestNumberInArray ${result.largestNumberInArray()}")
    }

    override val initialState: BaseViewState?
        get() = null

    suspend fun executeAllBFF() {
        println("executeAllBFF")
        runBlocking {
            val job = launch {
                learnUseCase.getAnyFact()
                println("getAnyFact Executed")
            }
            println("Hello")
            job.join()
            println("world")
        }
        /*coroutineScope {
            withContext(Dispatchers.IO) {
                val fact = launch {
                    delay(1000)
                    learnUseCase.getAnyFact()
                }

                launch {
                    try {
                        delay(500)
                        throw RuntimeException()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                val joke = launch {
                    delay(1000)
                    learnUseCase.getAnyJoke()
                }

                val result = Pair(joke, fact)
                println(result.first.isCompleted)
                println(result.second.isCompleted)
            }
        }*/
    }

}