package com.vimalraj.todoapplication.todo.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.core.views.GenericAlertDialog
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewEvents
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewModel
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewState
import com.vimalraj.todoapplication.ui.theme.TODOApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoViews(todoViewModel: TodoViewModel) {
    val todoViewState by todoViewModel.viewState.observeAsState()

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
                        if (todoViewState?.todoList.isNullOrEmpty().not()) {
                            todoViewModel.showDeleteAllAlert()
                        }
                    }) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = null,
                            tint = if (todoViewState?.todoList.isNullOrEmpty().not()) {
                                colorResource(R.color.black)
                            } else {
                                colorResource(R.color.black).copy(alpha = 0.4f)
                            }

                        )
                    }
                    IconButton(onClick = {
                        todoViewModel.openBottomSheet(false, -1)
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
                ScrollContent(todoViewModel, todoViewState)
            }
        }
    }
}


@Composable
fun ScrollContent(todoViewModel: TodoViewModel, todoViewState: TodoViewState?) {
    LaunchedEffect(Unit) {
        todoViewModel.fetchAllTask()
    }
    val todoViewEvents = todoViewModel.viewEvents.observeAsState()
    var showDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }


    when (todoViewEvents.value?.getEventHandling()) {
        TodoViewEvents.LaunchDeleteAllTask -> {
            showDialog = true
        }

        TodoViewEvents.LaunchAddTaskBottomSheet -> {
            showBottomSheet = true
        }

        else -> {
            // DO nothing
        }

    }

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
        ShowDeleteAllTaskDialog(showDialog = showDialog, onDismissed = {
            showDialog = false
        }, todoViewModel = todoViewModel)
    }

    ShowBottomSheetDialog(
        showBottomSheet = showBottomSheet,
        todoViewState = todoViewState,
        todoViewModel = todoViewModel,
        onDismissed = {
            showBottomSheet = false
        })
}

@Composable
fun ShowDeleteAllTaskDialog(
    showDialog: Boolean,
    onDismissed: () -> Unit,
    todoViewModel: TodoViewModel
) {
    if (showDialog) {
        GenericAlertDialog(
            dialogTitle = stringResource(R.string.are_you_sure_to_delete_all),
            dialogText = stringResource(R.string.delete_all_desc),
            onDismissRequest = {
                onDismissed.invoke()
            },
            onConfirmation = {
                onDismissed.invoke()
                todoViewModel.deleteAllTask()
            },
            icon = ImageVector.vectorResource(R.drawable.ic_warning_24)
        )
    }
}

@Composable
fun ShowBottomSheetDialog(
    showBottomSheet: Boolean,
    todoViewState: TodoViewState?,
    todoViewModel: TodoViewModel,
    onDismissed: () -> Unit,
) {
    if (showBottomSheet) {
        AddTaskModalSheet(todoViewState, todoViewModel, onDismissed = {
            onDismissed.invoke()
        })
    }
}
