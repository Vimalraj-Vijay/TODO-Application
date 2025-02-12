package com.vimalraj.todoapplication.learning.usecase

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.learning.model.Facts
import com.vimalraj.todoapplication.learning.model.Joke

interface LearnUseCase {

    suspend fun getAnyJoke(): ResultHandler<Joke>

    suspend fun getAnyFact(): ResultHandler<Facts>
}