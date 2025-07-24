package com.delhomme.jobbingtrack.features.followup.data.repositories

import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpPlatformDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpPlatformEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowUpPlatformRepository @Inject constructor(
    private val dao: FollowUpPlatformDao
) {
    val all: Flow<List<FollowUpPlatformEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpPlatformEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<FollowUpPlatformEntity>> = dao.getAllForUser(userId)

    suspend fun save(entity: FollowUpPlatformEntity): Long = dao.insert(entity)
    suspend fun update(entity: FollowUpPlatformEntity) = dao.update(entity)
    suspend fun delete(id: String, userId: String) =
        dao.softDeleteById(id, userId, System.currentTimeMillis())

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<FollowUpPlatformEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}