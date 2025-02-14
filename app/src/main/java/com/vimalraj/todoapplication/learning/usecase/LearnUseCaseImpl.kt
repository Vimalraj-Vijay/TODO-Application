package com.vimalraj.todoapplication.learning.usecase

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.learning.model.Facts
import com.vimalraj.todoapplication.learning.model.Joke
import com.vimalraj.todoapplication.learning.repository.LearnRepository
import kotlinx.coroutines.flow.Flow

class LearnUseCaseImpl(
    private val learnRepository: LearnRepository
) : LearnUseCase {

    override suspend fun getJoke(): Flow<ResultHandler<Joke>> {
        return learnRepository.getJoke()
    }

    override suspend fun getAnyJoke(): ResultHandler<Joke> {
        println("getAnyJoke")
        return learnRepository.getAnyJoke()
    }

    override suspend fun getAnyFact(): ResultHandler<Facts> {
        println("getAnyFact")
        return learnRepository.getAnyFact()
    }
}