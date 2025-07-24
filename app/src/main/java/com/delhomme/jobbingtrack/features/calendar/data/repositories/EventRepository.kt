package com.delhomme.jobbingtrack.features.calendar.data.repositories

import com.delhomme.jobbingtrack.features.calendar.data.dao.EventDao
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventWithRelations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(
    private val dao: EventDao
) {
    fun allForUser(userId: String): Flow<List<EventEntity>> = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<EventEntity>> = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<EventEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<EventEntity>> = dao.getDeletedForUser(userId)

    fun byId(id: String, userId: String): Flow<EventEntity?> = dao.getByIdForUser(id, userId)
    fun getEventWithRelations(id: String, userId: String): Flow<EventWithRelations?> = dao.getEventWithRelations(id, userId)
    suspend fun getWithRelations(id: String): EventWithRelations? = dao.getWithRelations(id)

    fun getEventsForDateRange(userId: String, startDate: Long, endDate: Long): Flow<List<EventEntity>> =
        dao.getEventsForDateRange(userId, startDate, endDate)

    fun getByApplicationId(userId: String, applicationId: String): Flow<List<EventEntity>> =
        dao.getByApplicationId(userId, applicationId)

    fun getByCompanyId(userId: String, companyId: String): Flow<List<EventEntity>> =
        dao.getByCompanyId(userId, companyId)

    fun getByContactId(userId: String, contactId: String): Flow<List<EventEntity>> =
        dao.getByContactId(userId, contactId)

    suspend fun save(event: EventEntity): Long = dao.insert(event)
    suspend fun update(event: EventEntity) = dao.update(event)
    suspend fun archive(ids: List<String>, userId: String) = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = dao.deleteAllForUser(userId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<EventEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)

    // Helper method pour DateRangeProvider
    fun getByDateRange(userId: String, fromTimestamp: Long, toTimestamp: Long): Flow<List<EventEntity>> {
        val query = androidx.sqlite.db.SimpleSQLiteQuery(
            "SELECT * FROM events WHERE userId = ? AND startDate BETWEEN ? AND ? AND isDeleted = 0 ORDER BY startDate ASC",
            arrayOf(userId, fromTimestamp, toTimestamp)
        )
        return dao.getByDateRange(query)
    }
}