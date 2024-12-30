package com.vimalraj.todoapplication.todo.usecase

import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity
import com.vimalraj.todoapplication.todo.repo.TodoLocalRepository
import javax.inject.Inject

class TodoUseCaseImpl @Inject constructor(
    private val todoLocalRepository: TodoLocalRepository
) : TodoUseCase {

    override suspend fun insertNewTodoTask(todoModelAndEntity: TodoModelAndEntity) {
        todoLocalRepository.insertNewTodoTask(todoModelAndEntity = todoModelAndEntity)
    }

    override suspend fun getAllTodoModelAndEntity(): List<TodoModelAndEntity> {
        return todoLocalRepository.getAllTodoModelAndEntity()
    }

    override suspend fun isTaskCompleted(isTaskCompleted: Boolean, id: Int, lastModified: String) {
        todoLocalRepository.isTaskCompleted(isTaskCompleted = isTaskCompleted, id = id, lastModified = lastModified)
    }

    override suspend fun deleteTaskUsingId(id: Int) {
        todoLocalRepository.deleteTaskUsingId(id)
    }

}