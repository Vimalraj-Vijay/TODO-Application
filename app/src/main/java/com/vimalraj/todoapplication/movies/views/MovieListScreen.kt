package com.vimalraj.todoapplication.movies.views

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.vimalraj.coremodule.HandleEvent
import com.vimalraj.coremodule.common.utils.CircularLoader
import com.vimalraj.coremodule.common.utils.ErrorScreen
import com.vimalraj.coremodule.common.utils.NoInternetAlertDialog
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.main.getMainNavigationItems
import com.vimalraj.todoapplication.main.launchSelectedScreen
import com.vimalraj.todoapplication.movies.data.MovieDetails
import com.vimalraj.todoapplication.movies.viewmodel.MovieListViewModel
import com.vimalraj.todoapplication.movies.viewmodel.MovieViewEvents
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(movieListViewModel: MovieListViewModel) {
    val activity = (LocalContext.current as? Activity)

    LaunchedEffect(Unit) {
        movieListViewModel.executeSuspend()
    }
    val movieViewState by movieListViewModel.viewState.collectAsStateWithLifecycle()
    val movieViewEvents by movieListViewModel.viewEvent.collectAsStateWithLifecycle()
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
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = colorResource(R.color.blue40),
                        ),
                        title = {
                            Text(
                                stringResource(R.string.movies),
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
                val movieList = movieViewState?.movieDetailsItem ?: emptyList()
                handleEvents(movieViewEvents, showNoInternetDialog)


                CircularLoader(
                    showLoader = movieViewState?.isLoading == true,
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

                LazyMovieGrid(innerPadding, movieDetailsList = movieList)
                ErrorScreen(showError = movieViewState?.isError == true)
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
    movieViewEvents: HandleEvent<MovieViewEvents?>,
    showNoInternetDialog: MutableState<Boolean>
) {
    when (movieViewEvents.getEventHandling()) {
        MovieViewEvents.LaunchNoInternetConnection -> {
            showNoInternetDialog.value = true
        }

        else -> {
            // Do Nothing
        }
    }
}


@Composable
fun LazyMovieGrid(innerPadding: PaddingValues, movieDetailsList: List<MovieDetails>) {
    if (movieDetailsList.isEmpty()) {
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(innerPadding)
    ) {
        items(items = movieDetailsList, key = { item -> item.hashCode() }) { item ->
            Card(
                modifier = Modifier
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.yellow40),
                ),
                content = {
                    Column {
                        AsyncImage(
                            model = item.posterURL,
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .background(color = colorResource(R.color.green))
                        )
                        Text(
                            item.title ?: "",
                            modifier = Modifier.padding(all = 8.dp),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                        Text(
                            text = stringResource(
                                R.string.ratings,
                                item.imdbRating ?: ""
                            ),
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(bottom = 8.dp),
                            fontSize = 14.sp
                        )
                    }
                }
            )
        }
    }
}
