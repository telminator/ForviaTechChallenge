package com.example.challenge.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.challenge.R
import com.example.challenge.presentation.component.AppItem
import com.example.challenge.presentation.component.ErrorView
import com.example.challenge.presentation.component.LoadingView
import com.example.challenge.presentation.viewmodel.AppsListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppsListScreen(
    uiState: AppsListUiState,
    onRefresh: () -> Unit,
    onAppClick: (Int) -> Unit
) {
    val pullRefreshState = rememberPullToRefreshState()

    val isRefreshing = when (uiState) {
        is AppsListUiState.Loading -> uiState.isRefreshing
        is AppsListUiState.Success -> uiState.isRefreshing
        is AppsListUiState.Error -> uiState.isRefreshing
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.apps_list_title)) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is AppsListUiState.Loading -> {
                    if (!uiState.isRefreshing) {
                        // Only show loading view for initial load, not during refresh
                        LoadingView()
                    }
                }

                is AppsListUiState.Success -> {
                    PullToRefreshBox(
                        isRefreshing = isRefreshing,
                        onRefresh = onRefresh,
                        state = pullRefreshState
                    ) {
                        LazyColumn(Modifier.fillMaxSize()) {
                            items(uiState.apps) { app ->
                                AppItem(
                                    app = app,
                                    onClick = { onAppClick(app.id) }
                                )
                            }
                        }
                    }
                }

                is AppsListUiState.Error -> {
                    ErrorView(
                        message = uiState.message,
                        onRetry = onRefresh
                    )
                }
            }
        }
    }
}