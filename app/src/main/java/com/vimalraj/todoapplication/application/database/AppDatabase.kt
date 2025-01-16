package com.vimalraj.todoapplication.application.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vimalraj.todoapplication.todo.database.dao.TodoDao
import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity

@Database(
    version = 2,
    entities = [TodoModelAndEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}