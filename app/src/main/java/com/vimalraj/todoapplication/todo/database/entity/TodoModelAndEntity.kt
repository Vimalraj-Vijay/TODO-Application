package com.vimalraj.todoapplication.todo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoModelAndEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val task: String = "",
    val priority: Priority = Priority.LOW,
    val createdAt: String = "",
    val isTaskCompleted: Boolean = false,
)
