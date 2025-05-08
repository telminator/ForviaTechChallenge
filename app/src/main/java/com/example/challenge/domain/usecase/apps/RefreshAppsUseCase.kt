package com.example.challenge.domain.usecase.apps

import com.example.challenge.domain.repository.AppsRepository
import javax.inject.Inject

class RefreshAppsUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.refreshApps()
    }
}