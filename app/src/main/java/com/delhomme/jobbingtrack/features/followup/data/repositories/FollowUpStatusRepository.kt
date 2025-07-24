package com.delhomme.jobbingtrack.features.followup.data.repositories

import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpStatusDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpStatusEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowUpStatusRepository @Inject constructor(
    private val dao: FollowUpStatusDao
) {
    val all: Flow<List<FollowUpStatusEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpStatusEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<FollowUpStatusEntity>> = dao.getAllForUser(userId)

    suspend fun save(entity: FollowUpStatusEntity): Long = dao.insert(entity)
    suspend fun update(entity: FollowUpStatusEntity) = dao.update(entity)
    suspend fun delete(id: String, userId: String) =
        dao.softDeleteById(id, userId, System.currentTimeMillis())

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<FollowUpStatusEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}