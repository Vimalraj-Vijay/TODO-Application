package com.vimalraj.todoapplication.learning.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.learning.viewmodel.LearnViewModel

@Composable
fun NewLearnView(innerPaddingValues: PaddingValues) {

    val learnViewModel = viewModel<LearnViewModel>()
    LaunchedEffect(Unit) {
        learnViewModel.executeAllBFF()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
            .background(color = colorResource(R.color.grey))
    ) {
        Text(text = "This is a dummy text")
    }
}