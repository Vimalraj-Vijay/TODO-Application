package com.vimalraj.todoapplication.movies.viewmodel

import com.vimalraj.coremodule.BaseEvents

sealed class MovieViewEvents : BaseEvents {

    data object LaunchNoInternetConnection : MovieViewEvents()

}