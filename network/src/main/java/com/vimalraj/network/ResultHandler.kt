package com.vimalraj.network

sealed class ResultHandler<GENERIC_RESPONSE>(
    val data: GENERIC_RESPONSE? = null,
    val message: String? = null,
    val accessDenied: Boolean = false
) {

    class Success<GENERIC_RESPONSE>(data: GENERIC_RESPONSE?) :
        ResultHandler<GENERIC_RESPONSE>(data = data)

    class Error<GENERIC_RESPONSE>(message: String?, data: GENERIC_RESPONSE? = null) :
        ResultHandler<GENERIC_RESPONSE>(data, message)

    class AccessDenied<GENERIC_RESPONSE>(accessDenied: Boolean) :
        ResultHandler<GENERIC_RESPONSE>(accessDenied = accessDenied)
}