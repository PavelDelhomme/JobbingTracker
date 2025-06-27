package com.delhomme.jobbingtrack

import android.app.Application
import androidx.room.Room
import com.delhomme.jobbingtrack.core.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class JobbingTrackApp : Application() {
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
            // pour une table rase de migration auto
            .fallbackToDestructiveMigration()
            .build()
    }
}