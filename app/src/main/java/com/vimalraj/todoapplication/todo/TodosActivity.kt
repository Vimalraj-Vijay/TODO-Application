package com.vimalraj.todoapplication.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewModel
import com.vimalraj.todoapplication.todo.views.TodoViews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodosActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoViews(todoViewModel)
        }
    }
}