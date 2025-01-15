package com.vimalraj.todoapplication.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.vimalraj.todoapplication.movies.viewmodel.MovieListViewModel
import com.vimalraj.todoapplication.movies.views.MovieListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {
    private val movieListViewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieListScreen(movieListViewModel)
        }
    }
}