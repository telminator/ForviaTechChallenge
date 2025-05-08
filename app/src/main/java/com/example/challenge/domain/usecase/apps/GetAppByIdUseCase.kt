package com.example.challenge.domain.usecase.apps

import com.example.challenge.domain.model.App
import com.example.challenge.domain.repository.AppsRepository
import javax.inject.Inject

class GetAppByIdUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    suspend operator fun invoke(id: Int): App? {
        return repository.getAppById(id)
    }
}