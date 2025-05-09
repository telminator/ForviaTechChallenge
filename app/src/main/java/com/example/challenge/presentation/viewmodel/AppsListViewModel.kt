package com.example.challenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.domain.model.App
import com.example.challenge.domain.usecase.apps.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppsListViewModel @Inject constructor(
    private val appUseCases: AppUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow<AppsListUiState>(AppsListUiState.Loading)
    val uiState: StateFlow<AppsListUiState> = _uiState.asStateFlow()

    init {
        loadApps()
    }

    fun loadApps() {
        viewModelScope.launch {
            _uiState.value = AppsListUiState.Loading

            appUseCases.getApps()
                .catch { e ->
                    _uiState.value = AppsListUiState.Error("Failed to load apps: ${e.message}")
                }
                .collect { apps ->
                    if (apps.isEmpty()) {
                        // If local db is empty, force refresh from network
                        refreshApps()
                    } else {
                        _uiState.value = AppsListUiState.Success(apps)
                    }
                }
        }
    }

    fun refreshApps() {
        viewModelScope.launch {
            appUseCases.refreshApps()
                .onFailure { e ->
                    _uiState.value = AppsListUiState.Error("Failed to refresh apps: ${e.message}")
                }
        }
    }

}

sealed class AppsListUiState {
    object Loading : AppsListUiState()
    data class Success(val apps: List<App>) : AppsListUiState()
    data class Error(val message: String) : AppsListUiState()
}