package com.vimalraj.todoapplication.todo.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.vimalraj.todoapplication.todo.database.dao.TodoDao
import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity

@Database(
    version = 2,
    entities = [TodoModelAndEntity::class],
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}