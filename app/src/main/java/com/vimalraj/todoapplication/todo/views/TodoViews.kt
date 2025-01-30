package com.vimalraj.todoapplication.todo.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.vimalraj.coremodule.common.utils.GenericAlertDialog
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewEvents
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewModel
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewState


@Composable
fun NewTodoView(innerPaddingValues: PaddingValues, todoViewModel: TodoViewModel) {
    val todoViewState by todoViewModel.viewState.collectAsState()

    Column(modifier = Modifier.padding(innerPaddingValues)) {
        ScrollContent(todoViewModel, todoViewState)
    }
}

@Composable
fun ScrollContent(todoViewModel: TodoViewModel, todoViewState: TodoViewState?) {
    LaunchedEffect(Unit) {
        todoViewModel.fetchAllTask()
    }
    val todoViewEvents = todoViewModel.viewEvent.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }


    when (todoViewEvents.value.getEventHandling()) {
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
            icon = ImageVector.vectorResource(R.drawable.ic_warning_24),
            iconColor = colorResource(R.color.red30),
            confirmText = stringResource(R.string.confirm),
            dismissText = stringResource(R.string.dismiss)
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
