package com.vimalraj.todoapplication.learning.repository

import com.vimalraj.network.ResultHandler
import com.vimalraj.todoapplication.learning.model.Facts
import com.vimalraj.todoapplication.learning.model.Joke

interface LearnRepository {

    suspend fun getAnyJoke(): ResultHandler<Joke>

    suspend fun getAnyFact(): ResultHandler<Facts>
}