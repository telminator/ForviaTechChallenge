package com.example.challenge.data.repository

import com.example.challenge.data.local.dao.AppDao
import com.example.challenge.data.local.mapper.toDomain
import com.example.challenge.data.remote.api.ApiService
import com.example.challenge.data.remote.mapper.toEntity
import com.example.challenge.domain.model.App
import com.example.challenge.domain.repository.AppsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val apiService: ApiService, private val appDao: AppDao
) : AppsRepository {

    override fun getApps(): Flow<List<App>> {
        return appDao.getAllApps().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun refreshApps(): Result<Unit> {
        return try {
            val response = apiService.getApps()
            val appDtoList = response.responses.listApps.datasets.all.data.list
            val appEntities = appDtoList.map { it.toEntity() }

            appDao.insertApps(appEntities)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAppById(id: Int): App? {
        return appDao.getAppById(id)?.toDomain()
    }
}