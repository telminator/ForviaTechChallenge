package com.example.challenge.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object AppsListRoute

@Serializable
data class AppDetailsRoute(val appId: Int)