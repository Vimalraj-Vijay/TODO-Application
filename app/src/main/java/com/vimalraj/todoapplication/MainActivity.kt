package com.vimalraj.todoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.vimalraj.todoapplication.main.NavigationDrawer
import com.vimalraj.todoapplication.main.SyncDataWorker
import com.vimalraj.todoapplication.ui.theme.TODOApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            workManager = WorkManager.getInstance(applicationContext)
            setOneTimeWorkRequest()
            TODOApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.white)
                ) {
                    NavigationDrawer()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        workManager.cancelAllWork()
    }

    private fun setOneTimeWorkRequest() {
        val constraints = Constraints.Builder()
            .build()
        /*val syncDataWorker = OneTimeWorkRequestBuilder<SyncDataWorker>()
            .setConstraints(constraints)
            .build()*/

        val syncDataWorker =
            PeriodicWorkRequestBuilder<SyncDataWorker>(
                repeatInterval = 15, TimeUnit.MINUTES,
            )
                .setConstraints(
                    constraints
                ).build()

        val workManager = WorkManager.getInstance(applicationContext)


        workManager.enqueue(syncDataWorker)


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(R.color.green),
                titleContentColor = MaterialTheme.colorScheme.primary,
            ), title = {
                Text("TODO", color = colorResource(R.color.black))
            }, actions = {
                // RowScope here, so these icons will be placed horizontally
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null,
                        tint = colorResource(R.color.black)
                    )
                }
            })
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
        }
    }
}