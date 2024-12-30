package com.vimalraj.todoapplication.todo.repo

import com.vimalraj.todoapplication.todo.database.TodoDatabase
import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity
import javax.inject.Inject

class TodoLocalRepositoryImpl @Inject constructor(
    private val todoDatabase: TodoDatabase
) : TodoLocalRepository {

    override suspend fun insertNewTodoTask(todoModelAndEntity: TodoModelAndEntity) {
        todoDatabase.todoDao().insert(todoModelAndEntity)
    }

    override suspend fun getAllTodoModelAndEntity(): List<TodoModelAndEntity> {
        return todoDatabase.todoDao().getAllTodoModelAndEntity()
    }

    override suspend fun deleteAllTask() {
        todoDatabase.todoDao().deleteAll()
    }

    override suspend fun deleteTaskUsingId(id: Int) {
        todoDatabase.todoDao().deleteTaskUsingId(id)
    }

}