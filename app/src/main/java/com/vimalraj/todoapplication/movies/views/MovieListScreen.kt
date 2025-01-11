package com.vimalraj.todoapplication.movies.views

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.vimalraj.coremodule.common.utils.CircularLoader
import com.vimalraj.coremodule.common.utils.ErrorScreen
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.movies.data.MovieDetails
import com.vimalraj.todoapplication.movies.viewmodel.MovieListViewModel
import com.vimalraj.todoapplication.ui.theme.TODOApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(movieListViewModel: MovieListViewModel) {
    LaunchedEffect(Unit) {
        movieListViewModel.fetchMovieListFromRemote()
    }
    val movieViewState by movieListViewModel.viewState.collectAsStateWithLifecycle()

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
            CircularLoader(
                showLoader = movieViewState?.isLoading == true,
                color = colorResource(R.color.red40),
                trackColor = colorResource(R.color.red_light)
            )
            LazyMovieGrid(innerPadding, movieDetailsList = movieList)
            ErrorScreen(showError = movieViewState?.isError == true)
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CardItem() {
    ErrorScreen(true)
}