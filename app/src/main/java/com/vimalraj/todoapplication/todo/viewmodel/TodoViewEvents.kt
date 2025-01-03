package com.vimalraj.todoapplication.todo.viewmodel

import com.vimalraj.todoapplication.core.BaseEvents

sealed class TodoViewEvents : BaseEvents {

    data object LaunchDeleteAllTask : TodoViewEvents()

    data object LaunchAddTaskBottomSheet : TodoViewEvents()
}