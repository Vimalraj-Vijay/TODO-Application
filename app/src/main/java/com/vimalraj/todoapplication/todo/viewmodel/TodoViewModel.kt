package com.vimalraj.todoapplication.todo.viewmodel

import androidx.lifecycle.viewModelScope
import com.vimalraj.todoapplication.core.HandleEvent
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
) : TypeBasedViewModel<TodoViewState, TodoViewEvents>() {

    fun fetchAllTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val getAllTask = async { todoUseCase.getAllTodoModelAndEntity() }.await()
            val unCompletedTodoList =
                getAllTask.filter { todoModelAndEntity: TodoModelAndEntity -> todoModelAndEntity.isTaskCompleted.not() }
                    .reversed()
            val completedTodoList =
                getAllTask.filter { todoModelAndEntity: TodoModelAndEntity -> todoModelAndEntity.isTaskCompleted }
            val finalTodoList = unCompletedTodoList + completedTodoList

            //println("getAllTask --> ${getAllTask.reversed()}")
            //println("unCompletedTodoList --> ${unCompletedTodoList.reversed()}")
            //println("completedTodoList --> $completedTodoList")
            //println("finalTodoList --> $finalTodoList")
            mutableViewState.postValue(
                TodoViewState(
                    todoList = finalTodoList
                )
            )
        }
    }

    fun openBottomSheet(isUpdate: Boolean, index: Int) {
        if (isUpdate) {
            updateTask(index)
        }
        mutableViewEvents.value =
            HandleEvent(eventContent = TodoViewEvents.LaunchAddTaskBottomSheet)
    }

    fun updateTask(index: Int) {
        mutableViewState.value = mutableViewState.value?.copy(
            isUpdateTask = true,
            selectedIndex = index
        )

    }

    fun updateTaskStatus(isTaskCompleted: Boolean, id: Int) {
        val date = getCurrentDateTime()
        val dateInString = date.toString(DD_MM_YYYY_T_FORMAT)

        viewModelScope.launch(Dispatchers.IO) {
            async {
                todoUseCase.isTaskCompleted(
                    isTaskCompleted = isTaskCompleted,
                    id = id,
                    lastModified = dateInString
                )
            }.await()
            fetchAllTask()
        }
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

    fun deleteAllTask() {
        viewModelScope.launch(Dispatchers.IO) {
            async { todoUseCase.deleteAll() }.await()
            fetchAllTask()
        }
    }

    fun showDeleteAllAlert() {
        mutableViewEvents.value = HandleEvent(eventContent = TodoViewEvents.LaunchDeleteAllTask)
    }
}