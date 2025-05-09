package com.example.challenge.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.challenge.util.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
class NewAppsAvailableWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            // Would check for new apps here
            // For exercise sake, just showing notification

            // Show notification
            val notificationHelper = NotificationHelper(applicationContext)
            notificationHelper.showNewAppsNotification()

            Timber.d("New APps Available Notification shown")

            Result.success()
        } catch (e: Exception) {
            Timber.e(e, "telmo Error in worker")
            Result.failure()
        }
    }
}
