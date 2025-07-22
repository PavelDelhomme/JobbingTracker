package com.delhomme.jobbingtrack.services.sync

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.delhomme.jobbingtrack.core.network.tokens.TokenManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val syncManager: SyncManager,
    private val tokenManager: TokenManager
) : CoroutineWorker(context, params) {
    private val TAG = "SyncWorker"

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Démarrage du worker de synchronisation")

            // Vérifier si l'utilisateur est connecté
            if (!tokenManager.isLoggedIn()) {
                Log.d(TAG, "Utilisateur non connecté, synchronisation annulée")
                return@withContext Result.failure()
            }

            // Exécuter la synchronisation
            val success = syncManager.performSync()

            return@withContext if (success) {
                Log.d(TAG, "Synchronisation réussie")
                Result.success()
            } else {
                Log.d(TAG, "Échec de la synchronisation, tentative de réessai programmée")
                Result.retry()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur lors de la synchronisation", e)
            return@withContext Result.retry()
        }
    }
}