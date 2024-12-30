package com.vimalraj.todoapplication.todo.usecase

import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity

interface TodoUseCase {

    suspend fun insertNewTodoTask(todoModelAndEntity: TodoModelAndEntity)

    suspend fun getAllTodoModelAndEntity(): List<TodoModelAndEntity>

    suspend fun deleteAllTask()

    suspend fun deleteTaskUsingId(id: Int)

}