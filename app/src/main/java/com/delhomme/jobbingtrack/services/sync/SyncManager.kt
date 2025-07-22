package com.delhomme.jobbingtrack.services.sync

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.delhomme.jobbingtrack.core.database.AppDatabase
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.network.ApiService
import com.delhomme.jobbingtrack.core.network.tokens.TokenManager
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ApiService,
    private val database: AppDatabase,
    private val tokenManager: TokenManager
) {
    private val TAG = "SyncManager"
    private val prefs: SharedPreferences = context.getSharedPreferences("sync_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val LAST_SYNC_TIMESTAMP = "last_sync_timestamp"
        private const val SYNC_INTERVAL = "sync_interval"
        private const val DEFAULT_SYNC_INTERVAL = 6L // Heures
        private const val SYNC_STATE = "sync_state"
        private const val SYNC_DEVICE_ID = "sync_device_id"
    }

    init {
        // Initialiser l'ID de l'appareil s'il n'existe pas encore
        if (!prefs.contains(SYNC_DEVICE_ID)) {
            prefs.edit().putString(SYNC_DEVICE_ID, UUID.randomUUID().toString()).apply()
        }
    }

    fun getDeviceId(): String {
        return prefs.getString(SYNC_DEVICE_ID, UUID.randomUUID().toString())!!
    }

    fun getLastSyncTimestamp(): Long {
        return prefs.getLong(LAST_SYNC_TIMESTAMP, 0)
    }

    private fun updateLastSyncTimestamp(timestamp: Long) {
        prefs.edit().putLong(LAST_SYNC_TIMESTAMP, timestamp).apply()
    }

    fun getSyncInterval(): Long {
        return prefs.getLong(SYNC_INTERVAL, DEFAULT_SYNC_INTERVAL)
    }

    fun setSyncInterval(hours: Long) {
        prefs.edit().putLong(SYNC_INTERVAL, hours).apply()
    }

    suspend fun performSync(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Démarrage de la synchronisation...")

                // Vérifier si l'utilisateur est connecté
                if (!tokenManager.isLoggedIn()) {
                    Log.e(TAG, "Utilisateur non connecté, synchronisation annulée")
                    return@withContext false
                }

                val userId = tokenManager.getUserId() ?: return@withContext false
                val lastSyncTime = getLastSyncTimestamp()
                val currentTime = System.currentTimeMillis()

                // 1. Collecte des modifications locales à envoyer au serveur
                val localChanges = collectLocalChanges(lastSyncTime, userId)

                // 2. Envoi des modifications locales au serveur
                if (localChanges.isNotEmpty()) {
                    Log.d(TAG, "Envoi des modifications locales: ${localChanges.keys}")
                    val clientSyncResponse = apiService.clientSync(localChanges)

                    if (!clientSyncResponse.isSuccessful) {
                        Log.e(TAG, "Échec de l'envoi des modifications locales: ${clientSyncResponse.code()}")
                        return@withContext false
                    }

                    // Mettre à jour les timestamps de synchronisation pour les entités envoyées
                    updateLocalSyncTimestamps(localChanges, currentTime)

                    Log.d(TAG, "Modifications locales envoyées avec succès")
                } else {
                    Log.d(TAG, "Aucune modification locale à envoyer")
                }

                // 3. Récupération des modifications du serveur
                Log.d(TAG, "Récupération des modifications du serveur depuis $lastSyncTime")
                val serverChanges = apiService.syncData(lastSyncTime)

                if (!serverChanges.isSuccessful) {
                    Log.e(TAG, "Échec de la récupération des modifications du serveur: ${serverChanges.code()}")
                    return@withContext false
                }

                val syncResponse = serverChanges.body()

                if (syncResponse != null) {
                    // 4. Application des modifications du serveur à la base de données locale
                    Log.d(TAG, "Application des modifications du serveur")
                    applyServerChanges(syncResponse, userId)

                    // 5. Mise à jour du timestamp de dernière synchronisation
                    updateLastSyncTimestamp(currentTime)

                    Log.d(TAG, "Synchronisation terminée avec succès")
                    return@withContext true
                } else {
                    Log.e(TAG, "Réponse de synchronisation vide")
                }

                return@withContext false
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors de la synchronisation", e)
                return@withContext false
            }
        }
    }

    private suspend fun updateLocalSyncTimestamps(changes: Map<String, List<Any>>, timestamp: Long) {
        withContext(Dispatchers.IO) {
            try {
                changes.forEach { (entityType, entities) ->
                    when (entityType) {
                        "applications" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.applicationDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "companies" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.companyDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "contacts" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.contactDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "calls" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.callDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "followups" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.followUpDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "interviews" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.interviewDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "events" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.eventDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "cvs" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.cvDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "educations" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.educationDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "experiences" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.experienceDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "projects" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.projectDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "skills" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.skillDao().updateSyncTimestamp(ids, timestamp)
                        }
                        "languages" -> {
                            val ids = entities.map { (it as BaseEntity).id }
                            database.languageDao().updateSyncTimestamp(ids, timestamp)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors de la mise à jour des timestamps de synchronisation", e)
                throw e
            }
        }
    }

    private suspend fun applyServerChanges(syncResponse: SyncResponse, userId: String) {
        withContext(Dispatchers.IO) {
            try {
                // Application des mises à jour
                syncResponse.updatedRecords.forEach { (entityType, entities) ->
                    Log.d(TAG, "Traitement des mises à jour pour $entityType: ${entities.size} éléments")

                    when (entityType) {
                        "applications" -> processUpdatedEntities(entities, database.applicationDao(), userId)
                        "companies" -> processUpdatedEntities(entities, database.companyDao(), userId)
                        "contacts" -> processUpdatedEntities(entities, database.contactDao(), userId)
                        "calls" -> processUpdatedEntities(entities, database.callDao(), userId)
                        "followups" -> processUpdatedEntities(entities, database.followUpDao(), userId)
                        "interviews" -> processUpdatedEntities(entities, database.interviewDao(), userId)
                        "events" -> processUpdatedEntities(entities, database.eventDao(), userId)
                        "cvs" -> processUpdatedEntities(entities, database.cvDao(), userId)
                        "educations" -> processUpdatedEntities(entities, database.educationDao(), userId)
                        "experiences" -> processUpdatedEntities(entities, database.experienceDao(), userId)
                        "projects" -> processUpdatedEntities(entities, database.projectDao(), userId)
                        "skills" -> processUpdatedEntities(entities, database.skillDao(), userId)
                        "languages" -> processUpdatedEntities(entities, database.languageDao(), userId)
                    }
                }

                // Application des suppressions
                syncResponse.deletedRecords.forEach { (entityType, ids) ->
                    Log.d(TAG, "Traitement des suppressions pour $entityType: ${ids.size} éléments")

                    when (entityType) {
                        "applications" -> processDeletedEntities(ids, database.applicationDao(), userId)
                        "companies" -> processDeletedEntities(ids, database.companyDao(), userId)
                        "contacts" -> processDeletedEntities(ids, database.contactDao(), userId)
                        "calls" -> processDeletedEntities(ids, database.callDao(), userId)
                        "followups" -> processDeletedEntities(ids, database.followUpDao(), userId)
                        "interviews" -> processDeletedEntities(ids, database.interviewDao(), userId)
                        "events" -> processDeletedEntities(ids, database.eventDao(), userId)
                        "cvs" -> processDeletedEntities(ids, database.cvDao(), userId)
                        "educations" -> processDeletedEntities(ids, database.educationDao(), userId)
                        "experiences" -> processDeletedEntities(ids, database.experienceDao(), userId)
                        "projects" -> processDeletedEntities(ids, database.projectDao(), userId)
                        "skills" -> processDeletedEntities(ids, database.skillDao(), userId)
                        "languages" -> processDeletedEntities(ids, database.languageDao(), userId)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors de l'application des changements du serveur", e)
                throw e
            }
        }
    }

    private suspend fun <T : BaseEntity> processUpdatedEntities(
        entities: List<Any>,
        dao: BaseDao<T>,
        userId: String
    ) {
        try {
            entities.forEach { entity ->
                // Convertir l'objet Any en T (entité spécifique)
                val json = gson.toJson(entity)
                val typedEntity = gson.fromJson(json, dao.javaClass.genericInterfaces[0].javaClass) as T

                // Vérifier si l'entité existe déjà en base de données
                val existingEntity = dao.getById(typedEntity.id, userId)

                if (existingEntity != null) {
                    // Mettre à jour uniquement si l'entité du serveur est plus récente
                    if (typedEntity.updatedAt > (existingEntity.updatedAt ?: 0)) {
                        // Mettre à jour l'entité
                        typedEntity.lastSyncAt = System.currentTimeMillis()
                        dao.update(typedEntity)
                    }
                } else {
                    // Insérer la nouvelle entité
                    typedEntity.lastSyncAt = System.currentTimeMillis()
                    dao.insert(typedEntity)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur lors du traitement des entités mises à jour", e)
            throw e
        }
    }

    private suspend fun <T : BaseEntity> processDeletedEntities(
        ids: List<String>,
        dao: BaseDao<T>,
        userId: String
    ) {
        try {
            ids.forEach { id ->
                // Marquer comme supprimé (soft delete)
                dao.softDeleteById(id, userId)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur lors du traitement des entités supprimées", e)
            throw e
        }
    }

    private suspend fun collectLocalChanges(lastSyncTime: Long, userId: String): Map<String, List<Any>> {
        val changes = mutableMapOf<String, List<Any>>()

        withContext(Dispatchers.IO) {
            try {
                // Applications
                val updatedApplications = database.applicationDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedApplications.isNotEmpty()) {
                    updatedApplications.forEach { it.updateEntityHash() }
                    changes["applications"] = updatedApplications
                }

                // Companies
                val updatedCompanies = database.companyDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedCompanies.isNotEmpty()) {
                    updatedCompanies.forEach { it.updateEntityHash() }
                    changes["companies"] = updatedCompanies
                }

                // Contacts
                val updatedContacts = database.contactDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedContacts.isNotEmpty()) {
                    updatedContacts.forEach { it.updateEntityHash() }
                    changes["contacts"] = updatedContacts
                }

                // Calls
                val updatedCalls = database.callDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedCalls.isNotEmpty()) {
                    updatedCalls.forEach { it.updateEntityHash() }
                    changes["calls"] = updatedCalls
                }

                // FollowUps
                val updatedFollowUps = database.followUpDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedFollowUps.isNotEmpty()) {
                    updatedFollowUps.forEach { it.updateEntityHash() }
                    changes["followups"] = updatedFollowUps
                }

                // Interviews
                val updatedInterviews = database.interviewDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedInterviews.isNotEmpty()) {
                    updatedInterviews.forEach { it.updateEntityHash() }
                    changes["interviews"] = updatedInterviews
                }

                // Events
                val updatedEvents = database.eventDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedEvents.isNotEmpty()) {
                    updatedEvents.forEach { it.updateEntityHash() }
                    changes["events"] = updatedEvents
                }

                // CVs
                val updatedCVs = database.cvDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedCVs.isNotEmpty()) {
                    updatedCVs.forEach { it.updateEntityHash() }
                    changes["cvs"] = updatedCVs
                }

                // Educations
                val updatedEducations = database.educationDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedEducations.isNotEmpty()) {
                    updatedEducations.forEach { it.updateEntityHash() }
                    changes["educations"] = updatedEducations
                }

                // Experiences
                val updatedExperiences = database.experienceDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedExperiences.isNotEmpty()) {
                    updatedExperiences.forEach { it.updateEntityHash() }
                    changes["experiences"] = updatedExperiences
                }

                // Projects
                val updatedProjects = database.projectDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedProjects.isNotEmpty()) {
                    updatedProjects.forEach { it.updateEntityHash() }
                    changes["projects"] = updatedProjects
                }

                // Skills
                val updatedSkills = database.skillDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedSkills.isNotEmpty()) {
                    updatedSkills.forEach { it.updateEntityHash() }
                    changes["skills"] = updatedSkills
                }

                // Languages
                val updatedLanguages = database.languageDao().getUpdatedSince(lastSyncTime, userId)
                if (updatedLanguages.isNotEmpty()) {
                    updatedLanguages.forEach { it.updateEntityHash() }
                    changes["languages"] = updatedLanguages
                }

                Log.d(TAG, "Collecte des changements locaux terminée: ${changes.size} types d'entités modifiés")
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors de la collecte des changements locaux", e)
                throw e
            }
        }

        return changes
    }
}