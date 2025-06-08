package com.delhomme.jobbingtrack.data.local.repository.event

import com.delhomme.jobbingtrack.data.local.dao.event.EventDao
import com.delhomme.jobbingtrack.data.local.entities.event.EventEntity
import kotlinx.coroutines.flow.Flow

class EventRepository(private val dao: EventDao) {
    fun allForUser(userId: String): Flow<List<EventEntity>>    = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<EventEntity>> = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<EventEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<EventEntity>> = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<EventEntity?>    = dao.getByIdForUser(id, userId)

    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<EventEntity>> = dao.getByDateRangeForUser(userId, from, to)

    /** création ou remplacement */
    suspend fun save(event: EventEntity)                      = dao.upsert(event)

    /** mise à jour existante */
    suspend fun update(event: EventEntity)                    = dao.update(event)

    /** batch */
    suspend fun archive(ids: List<String>, userId: String)    = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)    = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = dao.deleteForever(ids, userId)

    /** tout vider */
    suspend fun deleteAll(userId: String)                     = dao.deleteAllForUser(userId)
}