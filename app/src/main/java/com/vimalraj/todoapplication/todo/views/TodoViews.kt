package com.vimalraj.todoapplication.todo.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewModel
import com.vimalraj.todoapplication.ui.theme.TODOApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoViews(todoViewModel: TodoViewModel) {
    TODOApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.green),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ), title = {
                    Text(stringResource(R.string.todo), color = colorResource(R.color.black))
                }, actions = {
                    // RowScope here, so these icons will be placed horizontally
                    IconButton(onClick = {
                        todoViewModel.openAndCloseModelSheet(isOpen = true)
                    }) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = null,
                            tint = colorResource(R.color.black)
                        )
                    }
                })
            },
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                ScrollContent(todoViewModel)
            }
        }
    }
}


@Composable
fun ScrollContent(todoViewModel: TodoViewModel) {
    LaunchedEffect(Unit) {
        todoViewModel.fetchAllTask()
    }
    val todoViewState by todoViewModel.viewState.observeAsState()
    if (todoViewState?.todoList?.isEmpty() == true) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.no_task_found),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
            )
        }
    } else {
        TaskItem(taskList = todoViewState?.todoList, todoViewModel = todoViewModel)
    }

    AddTaskModalSheet(todoViewState, todoViewModel)
}