package com.vimalraj.todoapplication.multipleviewtype.view

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vimalraj.coremodule.HandleEvent
import com.vimalraj.coremodule.common.utils.CircularLoader
import com.vimalraj.coremodule.common.utils.ErrorScreen
import com.vimalraj.coremodule.common.utils.NoInternetAlertDialog
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.multipleviewtype.data.Features
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.MultiTypeViewEvents
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.MultiTypeViewModel
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.ViewType
import com.vimalraj.todoapplication.ui.theme.TODOApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiViewScreen(multiTypeViewModel: MultiTypeViewModel) {
    val activity = (LocalContext.current as? Activity)

    LaunchedEffect(Unit) {
        multiTypeViewModel.fetchMultiViewTypeJson()
    }

    val multiTypeViewState by multiTypeViewModel.viewState.collectAsStateWithLifecycle()
    val multiTypeViewEvents by multiTypeViewModel.viewEvent.collectAsStateWithLifecycle()
    val showNoInternetDialog = remember { mutableStateOf(false) }

    TODOApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(R.color.blue40),
                    ),
                    title = {
                        Text(
                            stringResource(R.string.lazy_column_with_multiple_view_type),
                            color = colorResource(R.color.black)
                        )
                    },
                )
            },
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
    }
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