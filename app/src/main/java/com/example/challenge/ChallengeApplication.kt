package com.example.challenge

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.challenge.worker.NewAppsAvailableWorker
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class ChallengeApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        // Initialize Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        scheduleNewAppsAvailableNotifications()
    }

    private fun scheduleNewAppsAvailableNotifications() {
        try {
            val notificationWorkRequest = PeriodicWorkRequestBuilder<NewAppsAvailableWorker>(
                15, TimeUnit.MINUTES
            )
                .build()

            WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "new_apps_available_work",
                ExistingPeriodicWorkPolicy.KEEP,
                notificationWorkRequest
            )
            Timber.d("Telmo, Work scheduled successfully")
        } catch (e: Exception) {
            Timber.e(e, "Error scheduling new apps available notifications ${e.message}")
        }

    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()
}
