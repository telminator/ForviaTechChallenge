package com.example.challenge.domain.usecase.apps

import javax.inject.Inject

data class AppUseCases @Inject constructor(
    val getApps: GetAppsUseCase,
    val refreshApps: RefreshAppsUseCase,
    val getAppById: GetAppByIdUseCase
)