package com.delhomme.jobbingtrack

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.room.Room
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.delhomme.jobbingtrack.core.database.AppDatabase
import com.delhomme.jobbingtrack.services.sync.SyncManager
import com.delhomme.jobbingtrack.services.sync.SyncWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class JobbingTrackApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var syncManager: SyncManager

    companion object {
        lateinit var database: AppDatabase; private set
    }

    override fun onCreate() {
        super.onCreate()

        // 1) initialisation de la base locale
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "jobbingtrack_db"
        )
            .fallbackToDestructiveMigration()
            .build()

        // 2) Configuration de la synchronisation périodique
        setupPeriodicSync()
    }

    private fun setupPeriodicSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncInterval = syncManager.getSyncInterval()

        val syncWorkRequest = PeriodicWorkRequestBuilder<SyncWorker>(
            syncInterval, TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "periodic_sync",
            ExistingPeriodicWorkPolicy.KEEP,
            syncWorkRequest
        )
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}