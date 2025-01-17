package com.vimalraj.todoapplication.multipleviewtype.view

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vimalraj.coremodule.HandleEvent
import com.vimalraj.coremodule.common.utils.CircularLoader
import com.vimalraj.coremodule.common.utils.ErrorScreen
import com.vimalraj.coremodule.common.utils.NoInternetAlertDialog
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.main.getMainNavigationItems
import com.vimalraj.todoapplication.main.launchSelectedScreen
import com.vimalraj.todoapplication.multipleviewtype.data.Features
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.MultiTypeViewEvents
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.MultiTypeViewModel
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.ViewType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiViewScreen(multiTypeViewModel: MultiTypeViewModel) {
    val activity = (LocalContext.current as? Activity)

    LaunchedEffect(Unit) {
        multiTypeViewModel.executeSuspend()
    }

    val multiTypeViewState by multiTypeViewModel.viewState.collectAsStateWithLifecycle()
    val multiTypeViewEvents by multiTypeViewModel.viewEvent.collectAsStateWithLifecycle()
    val showNoInternetDialog = remember { mutableStateOf(false) }

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
                        containerColor = colorResource(R.color.blue40),
                    ),
                        title = {
                            Text(
                                stringResource(R.string.lazy_column_with_multiple_view_type),
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
                        }
                    )
                }
            ) { innerPadding ->
                handleEvents(multiTypeViewEvents, showNoInternetDialog)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorResource(R.color.grey))
                ) {

                    CircularLoader(
                        showLoader = multiTypeViewState?.isLoading == true,
                        color = colorResource(R.color.red40),
                        trackColor = colorResource(R.color.red_light)
                    )

                    if (showNoInternetDialog.value) {
                        NoInternetAlertDialog {
                            showNoInternetDialog.value = false
                            activity?.finish()
                        }
                        return@Scaffold
                    }
                    LazyColumnMultiViewType(
                        innerPadding,
                        multipleViewsResponse = multiTypeViewState?.multipleViewsResponse
                    )
                    ErrorScreen(showError = multiTypeViewState?.isError == true)
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

private fun handleEvents(
    movieViewEvents: HandleEvent<MultiTypeViewEvents?>,
    showNoInternetDialog: MutableState<Boolean>
) {
    when (movieViewEvents.getEventHandling()) {
        MultiTypeViewEvents.LaunchNoInternetConnection -> {
            showNoInternetDialog.value = true
        }

        else -> {
            // Do Nothing
        }
    }
}

@Composable
fun LazyColumnMultiViewType(
    innerPadding: PaddingValues,
    multipleViewsResponse: MultipleViewsResponse?
) {
    if (multipleViewsResponse?.multipleViews?.isEmpty() == true) {
        return
    }
    multipleViewsResponse?.let {
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            multipleViewsResponse.multipleViews.forEach { section ->
                item {
                    Text(
                        text = section.sectionName ?: "",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    )
                }
                itemsIndexed(
                    items = section.features ?: emptyList()
                ) { _: Int, feature: Features ->
                    when (feature.viewType) {
                        ViewType.BILLING_REGULAR -> {
                            BillingRegularItem(features = feature)
                        }

                        ViewType.CLAIM_REGULAR -> {
                            ClaimsRegularItem(features = feature)
                        }

                        ViewType.VCS_REGULAR -> {
                            VCSRegularItem(features = feature)
                        }

                        else -> {
                            // Do nothing
                        }
                    }
                }
            }
        }
    }
}

fun getValueOrEmpty(value: String?): String {
    return value ?: ""
}