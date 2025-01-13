package com.vimalraj.todoapplication.movies.views

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
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
import com.vimalraj.todoapplication.movies.data.MovieDetails
import com.vimalraj.todoapplication.movies.viewmodel.MovieListViewModel
import com.vimalraj.todoapplication.movies.viewmodel.MovieViewEvents
import com.vimalraj.todoapplication.ui.theme.TODOApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(movieListViewModel: MovieListViewModel) {
    val activity = (LocalContext.current as? Activity)

    LaunchedEffect(Unit) {
        movieListViewModel.fetchMovieListFromRemote()
    }
    val movieViewState by movieListViewModel.viewState.collectAsStateWithLifecycle()
    val movieViewEvents by movieListViewModel.viewEvent.collectAsStateWithLifecycle()

    val showNoInternetDialog = remember { mutableStateOf(false) }

    TODOApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(R.color.blue40),
                    ),
                    title = {
                        Text(stringResource(R.string.movies), color = colorResource(R.color.black))
                    },
                )
            },
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
    }
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
