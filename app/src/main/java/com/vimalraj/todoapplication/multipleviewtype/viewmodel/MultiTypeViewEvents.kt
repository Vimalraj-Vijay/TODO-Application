package com.vimalraj.todoapplication.multipleviewtype.viewmodel

import com.vimalraj.coremodule.BaseEvents

sealed class MultiTypeViewEvents : BaseEvents {

    data object LaunchNoInternetConnection : MultiTypeViewEvents()

}