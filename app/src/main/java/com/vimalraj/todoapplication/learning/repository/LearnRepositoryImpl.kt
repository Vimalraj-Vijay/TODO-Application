package com.vimalraj.todoapplication.learning.repository

import com.vimalraj.network.RemoteApiError
import com.vimalraj.network.ResultHandler
import com.vimalraj.network.safeApiCall
import com.vimalraj.todoapplication.learning.api.LearningAPIClient
import com.vimalraj.todoapplication.learning.model.Facts
import com.vimalraj.todoapplication.learning.model.Joke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LearnRepositoryImpl(
    private val learningAPIClient: LearningAPIClient
) : LearnRepository {

    override suspend fun getJoke(): Flow<ResultHandler<Joke>> {
        val jokeResponse: Flow<ResultHandler<Joke>> = flow {
            emit(ResultHandler.AccessDenied)

            val response = safeApiCall {
                val url = "https://codingdev.free.beeceptor.com/joke"
                learningAPIClient.getAnyJoke(url = url)
            }
            emit(response)
        }
        return jokeResponse.map { res ->
            if (res is ResultHandler.Success) {
                val modifiedData = res.data.joke.filter { it in "aeiouAEIOU" }
                val modifiedJoke = res.data.copy(joke = modifiedData)

                ResultHandler.Success(data = modifiedJoke)
            } else {
                res
            }
        }.catch { ex ->
            ex.printStackTrace()
            ResultHandler.Error(
                remoteApiError = RemoteApiError.UNEXPECTED_ERROR,
                exception = Throwable(RemoteApiError.UNEXPECTED_ERROR.exceptionMessage.toString())
            )
        }
    }

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