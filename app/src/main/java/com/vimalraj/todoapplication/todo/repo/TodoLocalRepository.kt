package com.vimalraj.todoapplication.todo.repo

import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity

interface TodoLocalRepository {

    suspend fun insertNewTodoTask(todoModelAndEntity: TodoModelAndEntity)

    suspend fun getAllTodoModelAndEntity(): List<TodoModelAndEntity>

    suspend fun deleteAllTask()

    suspend fun deleteTaskUsingId(id: Int)
}