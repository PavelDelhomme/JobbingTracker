package com.delhomme.jobbingtrack.features.calendar.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.core.common.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao : DateRangeProvider<EventEntity>, BaseDao<EventEntity> {
    override val tableName: String get() = "events"
    override val dateColumn: String get() = "start_date"

    @Query("SELECT * FROM events WHERE userId = :userId ORDER BY startDate DESC")
    fun getAllForUser(userId: String): Flow<List<EventEntity>>

    @Transaction
    @Query("SELECT * FROM events WHERE id = :id")
    suspend fun getWithRelations(id: String): EventWithRelations?

    @Query("""
        SELECT * FROM events
        WHERE userId = :userId
        AND isDeleted = 0
        AND isArchived = 0
        ORDER BY startDate ASC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<EventEntity>>

    @Query("""
        SELECT * FROM events
        WHERE userId = :userId
        AND isArchived = 1
        ORDER BY startDate DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<EventEntity>>

    @Query("""
        SELECT * FROM events
        WHERE userId = :userId
        AND isDeleted = 1
        ORDER BY startDate DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<EventEntity>>

    @Query("SELECT * FROM events WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<EventEntity?>

    @Transaction
    @Query("SELECT * FROM events WHERE id = :id AND userId = :userId")
    fun getEventWithRelations(id: String, userId: String): Flow<EventWithRelations?>

    @Query("""
        SELECT * FROM events
        WHERE userId = :userId
        AND isDeleted = 0
        AND startDate >= :startDate
        AND startDate < :endDate
        ORDER BY startDate ASC
    """)
    fun getEventsForDateRange(userId: String, startDate: Long, endDate: Long): Flow<List<EventEntity>>

    @Query("""
        SELECT * FROM events
        WHERE userId = :userId
        AND isDeleted = 0
        AND applicationId = :applicationId
        ORDER BY startDate ASC
    """)
    fun getByApplicationId(userId: String, applicationId: String): Flow<List<EventEntity>>

    @Query("""
        SELECT * FROM events
        WHERE userId = :userId
        AND isDeleted = 0
        AND companyId = :companyId
        ORDER BY startDate ASC
    """)
    fun getByCompanyId(userId: String, companyId: String): Flow<List<EventEntity>>

    @Query("""
        SELECT * FROM events
        WHERE userId = :userId
        AND isDeleted = 0
        AND contactId = :contactId
        ORDER BY startDate ASC
    """)
    fun getByContactId(userId: String, contactId: String): Flow<List<EventEntity>>

    @Query("UPDATE events SET isArchived = 1, archivedAt = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE events SET isDeleted = 1, deletedAt = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE events SET isDeleted = 0, deletedAt = NULL WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM events WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM events WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @RawQuery(observedEntities = [EventEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<EventEntity>>

    @Query("""
        SELECT * FROM events 
        WHERE userId = :userId 
        AND updatedAt > :timestamp 
        AND (lastSyncAt IS NULL OR updatedAt > lastSyncAt)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<EventEntity>

    @Query("UPDATE events SET lastSyncAt = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM events WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): EventEntity?

    @Query("UPDATE events SET isDeleted = 1, deletedAt = :timestamp WHERE id = :id AND userId = :userId")
    override suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}