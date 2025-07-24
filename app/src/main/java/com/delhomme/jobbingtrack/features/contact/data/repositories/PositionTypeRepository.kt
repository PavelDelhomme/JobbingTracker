package com.delhomme.jobbingtrack.features.contact.data.repositories

import com.delhomme.jobbingtrack.features.contact.data.dao.PositionTypeDao
import com.delhomme.jobbingtrack.features.contact.data.entities.PositionTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PositionTypeRepository @Inject constructor(
    private val dao: PositionTypeDao
) {
    val all: Flow<List<PositionTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<PositionTypeEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<PositionTypeEntity>> = dao.getAllForUser(userId)

    suspend fun save(positionType: PositionTypeEntity) = dao.insert(positionType)
    suspend fun update(positionType: PositionTypeEntity) = dao.update(positionType)
    suspend fun delete(id: String, userId: String) = dao.softDeleteById(id, userId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<PositionTypeEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}