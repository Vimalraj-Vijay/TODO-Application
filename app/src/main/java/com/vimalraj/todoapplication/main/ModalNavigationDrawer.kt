package com.vimalraj.todoapplication.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.movies.views.NewMoviesListView
import com.vimalraj.todoapplication.multipleviewtype.view.NewMultiViewTypeView
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewModel
import com.vimalraj.todoapplication.todo.views.NewTodoView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    var selectedItem by rememberSaveable { mutableStateOf(NavItemType.TODO) }

    val todoViewModel = viewModel<TodoViewModel>()
    val todoViewState by todoViewModel.viewState.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(R.color.blue40),
                    ),
                        title = {
                            Text(
                                stringResource(getTitleRes(selectedItemIndex)),
                                color = colorResource(R.color.black)
                            )
                        },
                        navigationIcon = {
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
                        },
                        actions = {
                            if (selectedItem == NavItemType.TODO) {
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
                        }
                    )
                }
            ) { innerPadding ->
                when (selectedItem) {
                    NavItemType.TODO -> NewTodoView(
                        innerPaddingValues = innerPadding,
                        todoViewModel = todoViewModel
                    )

                    NavItemType.MOVIES -> NewMoviesListView(innerPaddingValues = innerPadding)
                    NavItemType.MULTI_TYPE_VIEWS -> NewMultiViewTypeView(innerPaddingValues = innerPadding)
                }
            }
        },
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = colorResource(R.color.white)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.welcome),
                    modifier = Modifier.padding(16.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
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
                            selectedItem = mainNavigationItems.route
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        selected = index == selectedItemIndex,
                        icon = {
                            Icon(
                                modifier = Modifier.size(26.dp),
                                tint = colorResource(R.color.red30),
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

fun getTitleRes(selectedIndex: Int): Int {
    return getMainNavigationItems()[selectedIndex].title
}