package com.example.challenge.domain.repository

import com.example.challenge.domain.model.App
import kotlinx.coroutines.flow.Flow

interface AppsRepository {
    fun getApps(): Flow<List<App>>
    suspend fun refreshApps(): Result<Unit>
    suspend fun getAppById(id: Int): App?
}