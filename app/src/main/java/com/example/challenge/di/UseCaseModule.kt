package com.example.challenge.di

import com.example.challenge.domain.repository.AppsRepository
import com.example.challenge.domain.usecase.apps.AppDownloadUseCase
import com.example.challenge.domain.usecase.apps.AppUseCases
import com.example.challenge.domain.usecase.apps.GetAppByIdUseCase
import com.example.challenge.domain.usecase.apps.GetAppsUseCase
import com.example.challenge.domain.usecase.apps.RefreshAppsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAppUseCases(repository: AppsRepository): AppUseCases {
        return AppUseCases(
            getApps = GetAppsUseCase(repository),
            getAppById = GetAppByIdUseCase(repository),
            refreshApps = RefreshAppsUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideAttemptAppDownloadUseCase(): AppDownloadUseCase {
        return AppDownloadUseCase()
    }
}