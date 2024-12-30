package com.vimalraj.todoapplication.todo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoModelAndEntity: TodoModelAndEntity)

    @Query("SELECT * FROM TodoModelAndEntity")
    suspend fun getAllTodoModelAndEntity(): List<TodoModelAndEntity>

    @Query("DELETE FROM TodoModelAndEntity")
    suspend fun deleteAll()

    @Query("DELETE FROM TodoModelAndEntity WHERE id = :id")
    suspend fun deleteTaskUsingId(id: Int)
}