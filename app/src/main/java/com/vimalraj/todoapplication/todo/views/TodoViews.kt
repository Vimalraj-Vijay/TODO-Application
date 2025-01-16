package com.vimalraj.todoapplication.todo.views

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vimalraj.coremodule.common.utils.GenericAlertDialog
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.main.getMainNavigationItems
import com.vimalraj.todoapplication.main.launchSelectedScreen
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewEvents
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewModel
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoViews(todoViewModel: TodoViewModel) {
    val activity = (LocalContext.current as? Activity)

    val todoViewState by todoViewModel.viewState.collectAsState()
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(R.color.green),
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ), title = {
                        Text(
                            stringResource(R.string.todo),
                            color = colorResource(R.color.black)
                        )
                    }, navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
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
                    }

                    )
                }
            ) { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    ScrollContent(todoViewModel, todoViewState)
                }
            }
        },
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = colorResource(R.color.white)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                getMainNavigationItems().forEachIndexed { index, mainNavigationItems ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = colorResource(R.color.grey),
                            unselectedContainerColor = colorResource(R.color.white),
                        ),
                        label = {
                            Text(text = stringResource(mainNavigationItems.title))
                        },
                        onClick = {
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                            launchSelectedScreen(
                                context = context,
                                route = mainNavigationItems.route
                            )
                            activity?.finish()
                        },
                        selected = index == selectedItemIndex,
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(mainNavigationItems.icon),
                                contentDescription = ""
                            )
                        },
                    )
                }
            }
        }
    )
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
