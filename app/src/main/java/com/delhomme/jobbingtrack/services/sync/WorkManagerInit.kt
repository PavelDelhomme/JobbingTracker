package com.delhomme.jobbingtrack.services.sync

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit

class WorkManagerInitializer : Initializer<WorkManager> {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface WorkManagerInitializerEntryPoint {
        fun workerFactory(): HiltWorkerFactory
        fun syncManager(): SyncManager
    }

    override fun create(context: Context): WorkManager {
        val entryPoint = EntryPointAccessors.fromApplication(
            context.applicationContext,
            WorkManagerInitializerEntryPoint::class.java
        )

        val configuration = Configuration.Builder()
            .setWorkerFactory(entryPoint.workerFactory())
            .build()

        WorkManager.initialize(context, configuration)

        setupPeriodicSync(context, entryPoint.syncManager())

        return WorkManager.getInstance(context)
    }

    private fun setupPeriodicSync(context: Context, syncManager: SyncManager) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncInterval = syncManager.getSyncInterval()

        val syncWorkRequest = PeriodicWorkRequestBuilder<SyncWorker>(
            syncInterval, TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "periodic_sync",
            ExistingPeriodicWorkPolicy.KEEP,
            syncWorkRequest
        )
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}