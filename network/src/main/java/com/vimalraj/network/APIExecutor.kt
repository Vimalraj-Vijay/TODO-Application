package com.vimalraj.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class APIExecutor @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun <T> executeGETCall(url: String): ResultHandler<T> {
        var response: Response<T>? = null
        return withContext(Dispatchers.IO) {
            async { response = apiClient.handleGETMethod(url = url) }.await()
            return@withContext if (response != null && response?.isSuccessful == true) {
                ResultHandler.Success(data = response!!.body())
            } else {
                ResultHandler.Error(message = "Something went wrong")
            }
        }
    }
}