package com.vimalraj.todoapplication.multipleviewtype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.vimalraj.todoapplication.multipleviewtype.view.MultiViewScreen
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.MultiTypeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MultipleViewTypeColumnActivity : ComponentActivity() {
    private val multiTypeViewModel: MultiTypeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultiViewScreen(multiTypeViewModel = multiTypeViewModel)
        }
    }
}