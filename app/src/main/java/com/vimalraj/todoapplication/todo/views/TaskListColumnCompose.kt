package com.vimalraj.todoapplication.todo.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.todo.database.entity.Priority
import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewModel

@Composable
fun TaskItem(taskList: List<TodoModelAndEntity>?, todoViewModel: TodoViewModel) {
    taskList?.let {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            itemsIndexed(
                taskList,
            ) { index, item ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    onClick = {
                        todoViewModel.updateTask(index)
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = backgroundColor(item.priority),
                    ),
                    content = {
                        TaskListItem(todo = item, isTaskCompletedClick = {
                            todoViewModel.updateTaskStatus(it, taskList[index].id)
                        })
                    })

            }

        }
    }
}

@Composable
private fun backgroundColor(priority: Priority): Color {
    return when (priority) {
        Priority.LOW -> {
            colorResource(R.color.blue40)
        }

        Priority.MEDIUM -> {
            colorResource(R.color.yellow40)
        }

        else -> {
            colorResource(R.color.red40)
        }
    }
}