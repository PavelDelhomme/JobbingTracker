package com.delhomme.jobbingtrack.features.followup.data.repositories

import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpTypeDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowUpTypeRepository @Inject constructor(
    private val dao: FollowUpTypeDao
) {
    val all: Flow<List<FollowUpTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpTypeEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<FollowUpTypeEntity>> = dao.getAllForUser(userId)

    suspend fun save(entity: FollowUpTypeEntity): Long = dao.insert(entity)
    suspend fun update(entity: FollowUpTypeEntity) = dao.update(entity)
    suspend fun delete(id: String, userId: String) =
        dao.softDeleteById(id, userId, System.currentTimeMillis())

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<FollowUpTypeEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}