package com.vimalraj.todoapplication.todo.viewmodel

import androidx.lifecycle.viewModelScope
import com.vimalraj.todoapplication.core.BaseEvents
import com.vimalraj.todoapplication.core.TypeBasedViewModel
import com.vimalraj.todoapplication.core.getCurrentDateTime
import com.vimalraj.todoapplication.core.toString
import com.vimalraj.todoapplication.todo.TodoConstants.DD_MM_YYYY_T_FORMAT
import com.vimalraj.todoapplication.todo.database.entity.Priority
import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity
import com.vimalraj.todoapplication.todo.usecase.TodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoUseCase: TodoUseCase
) : TypeBasedViewModel<TodoViewState, BaseEvents>() {

    fun fetchAllTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val getAllTask = async { todoUseCase.getAllTodoModelAndEntity() }.await()
            mutableViewState.postValue(
                TodoViewState(
                    todoList = getAllTask.reversed()
                )
            )
        }
    }

    fun openAndCloseModelSheet(isOpen: Boolean) {
        mutableViewState.value = mutableViewState.value?.copy(
            isBottomSheetOpen = isOpen
        )
    }

    fun updateTask(index: Int) {
        mutableViewState.value = mutableViewState.value?.copy(
            isBottomSheetOpen = true,
            isUpdateTask = true,
            selectedIndex = index
        )

    }

    fun insertOrUpdateTask(index: Int = -1, task: String, priority: String) {
        val date = getCurrentDateTime()
        val dateInString = date.toString(DD_MM_YYYY_T_FORMAT)

        var todoModelAndEntity = TodoModelAndEntity(
            task = task,
            priority = Priority.valueOf(priority),
            createdAt = dateInString
        )

        if (index != -1) {
            todoModelAndEntity = todoModelAndEntity.copy(
                id = mutableViewState.value?.todoList?.get(index)?.id ?: -1,
            )
        }

        insertTodo(todoModelAndEntity = todoModelAndEntity)
        openAndCloseModelSheet(isOpen = false)

    }

    private fun insertTodo(todoModelAndEntity: TodoModelAndEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            async { todoUseCase.insertNewTodoTask(todoModelAndEntity = todoModelAndEntity) }.await()
            fetchAllTask()
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            async { todoUseCase.deleteTaskUsingId(id = id) }.await()
            fetchAllTask()
        }
    }
}