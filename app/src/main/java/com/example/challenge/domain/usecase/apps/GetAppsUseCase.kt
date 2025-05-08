package com.example.challenge.domain.usecase.apps

import com.example.challenge.domain.model.App
import com.example.challenge.domain.repository.AppsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    operator fun invoke(): Flow<List<App>> {
        return repository.getApps()
    }
}