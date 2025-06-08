package com.delhomme.jobbingtrack

import android.app.Application
import androidx.room.Room
import com.delhomme.jobbingtrack.data.local.AppDatabase
import com.delhomme.jobbingtrack.data.api.ApiClient


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

        // 2) Initialisation de l'API client (pour interceptor JWT)
        ApiClient.init(this)
    }
}