package com.delhomme.jobbingtrack.features.calendar.data.repositories

import com.delhomme.jobbingtrack.features.calendar.data.dao.EventTypeDao
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventTypeRepository @Inject constructor(
    private val dao: EventTypeDao
) {
    val all: Flow<List<EventTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<EventTypeEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<EventTypeEntity>> = dao.getAllForUser(userId)

    suspend fun save(entity: EventTypeEntity): Long = dao.insert(entity)
    suspend fun update(entity: EventTypeEntity) = dao.update(entity)
    suspend fun delete(id: String, userId: String) =
        dao.softDeleteById(id, userId, System.currentTimeMillis())

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<EventTypeEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}