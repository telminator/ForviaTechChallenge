package com.example.challenge.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.challenge.domain.model.App
import com.example.challenge.domain.usecase.apps.AppUseCases
import com.example.challenge.domain.usecase.apps.AppDownloadUseCase
import com.example.challenge.presentation.navigation.AppDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailViewModel @Inject constructor(
    private val appUseCases: AppUseCases,
    private val attemptAppDownloadUseCase: AppDownloadUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route = savedStateHandle.toRoute<AppDetailsRoute>()
    private val appId: Int = route.appId

    private val _uiState = MutableStateFlow<AppDetailsUiState>(AppDetailsUiState.Loading)
    val uiState: StateFlow<AppDetailsUiState> = _uiState.asStateFlow()

    private val _downloadState = MutableStateFlow<DownloadState>(DownloadState.Idle)
    val downloadState: StateFlow<DownloadState> = _downloadState.asStateFlow()

    init {
        loadAppDetails()
    }

    fun loadAppDetails() {
        viewModelScope.launch {
            _uiState.value = AppDetailsUiState.Loading

            val app = appUseCases.getAppById(appId)
            if (app != null) {
                _uiState.value = AppDetailsUiState.Success(app)
            } else {
                _uiState.value = AppDetailsUiState.Error("App not found")
            }
        }
    }

    fun downloadApp() {
        viewModelScope.launch {
            _downloadState.value = DownloadState.ShowingWarning
        }
    }

    fun dismissWarning() {
        _downloadState.value = DownloadState.Idle
    }
}

sealed class AppDetailsUiState {
    object Loading : AppDetailsUiState()
    data class Success(val app: App) : AppDetailsUiState()
    data class Error(val message: String) : AppDetailsUiState()
}

sealed class DownloadState {
    object Idle : DownloadState()
    object ShowingWarning : DownloadState()
}
