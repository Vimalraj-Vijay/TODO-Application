package com.vimalraj.todoapplication.learning.repository

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.learning.model.Facts
import com.vimalraj.todoapplication.learning.model.Joke
import kotlinx.coroutines.flow.Flow

interface LearnRepository {

    suspend fun getJoke(): Flow<ResultHandler<Joke>>

    suspend fun getAnyJoke(): ResultHandler<Joke>

    suspend fun getAnyFact(): ResultHandler<Facts>
}