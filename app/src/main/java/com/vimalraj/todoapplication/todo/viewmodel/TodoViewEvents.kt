package com.vimalraj.todoapplication.todo.viewmodel

import com.vimalraj.coremodule.BaseEvents


sealed class TodoViewEvents : BaseEvents {

    data object LaunchDeleteAllTask : TodoViewEvents()

    data object LaunchAddTaskBottomSheet : TodoViewEvents()
}