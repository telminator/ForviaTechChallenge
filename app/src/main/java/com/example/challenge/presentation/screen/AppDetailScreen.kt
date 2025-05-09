package com.example.challenge.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.challenge.R
import com.example.challenge.presentation.component.AppDetail
import com.example.challenge.presentation.component.ErrorView
import com.example.challenge.presentation.component.LoadingView
import com.example.challenge.presentation.component.WarningDialog
import com.example.challenge.presentation.viewmodel.AppDetailsUiState
import com.example.challenge.presentation.viewmodel.DownloadState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailScreen(
    uiState: AppDetailsUiState,
    downloadState: DownloadState,
    onBackClick: () -> Unit,
    onDownloadClick: () -> Unit,
    onDismissWarning: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_details_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back_button_content_description)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when (uiState) {
            is AppDetailsUiState.Loading -> {
                LoadingView(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is AppDetailsUiState.Success -> {
                AppDetail(
                    app = uiState.app,
                    onDownloadClick = onDownloadClick,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is AppDetailsUiState.Error -> {
                ErrorView(
                    message = uiState.message,
                    onRetry = onBackClick, // Just go back on error
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }

        // Show warning dialog if needed
        if (downloadState is DownloadState.ShowingWarning) {
            WarningDialog(onDismiss = onDismissWarning)
        }
    }
}