package com.example.challenge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.challenge.presentation.screen.AppDetailScreen
import com.example.challenge.presentation.screen.AppsListScreen
import com.example.challenge.presentation.viewmodel.AppDetailViewModel
import com.example.challenge.presentation.viewmodel.AppsListViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppsListRoute
    ) {
        composable<AppsListRoute> {
            val viewModel: AppsListViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            AppsListScreen(
                uiState = uiState,
                onRefresh = viewModel::refreshApps,
                onAppClick = { appId ->
                    navController.navigate(AppDetailsRoute(appId))
                }
            )
        }

        composable<AppDetailsRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<AppDetailsRoute>()
            val viewModel: AppDetailViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            val downloadState by viewModel.downloadState.collectAsState()

            AppDetailScreen(
                uiState = uiState,
                downloadState = downloadState,
                onBackClick = {
                    navController.popBackStack()
                },
                onDownloadClick = viewModel::downloadApp,
                onDismissWarning = viewModel::dismissWarning
            )
        }
    }
}