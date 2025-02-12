package com.vimalraj.todoapplication.learning.repository

import com.vimalraj.network.ResultHandler
import com.vimalraj.network.safeApiCall
import com.vimalraj.todoapplication.learning.api.LearningAPIClient
import com.vimalraj.todoapplication.learning.model.Facts
import com.vimalraj.todoapplication.learning.model.Joke

class LearnRepositoryImpl(
    private val learningAPIClient: LearningAPIClient
) : LearnRepository {

    override suspend fun getAnyJoke(): ResultHandler<Joke> {
        return safeApiCall {
            val url = "https://codingdev.free.beeceptor.com/joke"
            learningAPIClient.getAnyJoke(url = url)
        }
    }

    override suspend fun getAnyFact(): ResultHandler<Facts> {
        return safeApiCall {
            val url = "https://codingdev.free.beeceptor.com/facts"
            learningAPIClient.getAnyFacts(url = url)
        }
    }
}