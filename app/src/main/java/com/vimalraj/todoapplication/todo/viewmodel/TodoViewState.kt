package com.vimalraj.todoapplication.todo.viewmodel

import com.vimalraj.todoapplication.core.BaseViewState
import com.vimalraj.todoapplication.todo.database.entity.Priority
import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity

data class TodoViewState(
    val todoList: List<TodoModelAndEntity> = emptyList(),
    val isBottomSheetOpen: Boolean = false,
    val isUpdateTask: Boolean = false,
    val selectedIndex: Int = -1,
    val dropDownList: List<String> = listOf(
        Priority.LOW.name,
        Priority.MEDIUM.name,
        Priority.HIGH.name
    )
) : BaseViewState
