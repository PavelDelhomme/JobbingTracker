package com.delhomme.jobbingtrack.features.calendar.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventTypeDao : BaseDao<EventTypeEntity> {
    @Query("SELECT * FROM event_types WHERE isDeleted = 0")
    fun getAll(): Flow<List<EventTypeEntity>>

    @Query("SELECT * FROM event_types WHERE id = :id AND isDeleted = 0 LIMIT 1")
    fun getById(id: String): Flow<EventTypeEntity?>

    @Query("SELECT * FROM event_types WHERE userId = :userId AND isDeleted = 0")
    fun getAllForUser(userId: String): Flow<List<EventTypeEntity>>

    @Query("""
        SELECT * FROM event_types 
        WHERE userId = :userId 
        AND updatedAt > :timestamp 
        AND (lastSyncAt IS NULL OR updatedAt > lastSyncAt)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<EventTypeEntity>

    @Query("UPDATE event_types SET lastSyncAt = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM event_types WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): EventTypeEntity?

    @Query("UPDATE event_types SET isDeleted = 1, deletedAt = :timestamp WHERE id = :id AND userId = :userId")
    override suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}