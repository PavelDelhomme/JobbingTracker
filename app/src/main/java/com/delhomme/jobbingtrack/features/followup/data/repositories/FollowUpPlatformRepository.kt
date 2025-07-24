package com.delhomme.jobbingtrack.features.followup.data.repositories

import com.delhomme.jobbingtrack.features.followup.data.dao.FollowUpPlatformDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpPlateformEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowUpPlatformRepository @Inject constructor(
    private val dao: FollowUpPlatformDao
) {
    val all: Flow<List<FollowUpPlateformEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpPlateformEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<FollowUpPlateformEntity>> = dao.getAllForUser(userId)

    suspend fun save(followUpPlatform: FollowUpPlateformEntity) = dao.insert(followUpPlatform)
    suspend fun update(followUpPlatform: FollowUpPlateformEntity) = dao.update(followUpPlatform)
    suspend fun delete(id: String, userId: String) = dao.softDeleteById(id, userId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<FollowUpPlateformEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}