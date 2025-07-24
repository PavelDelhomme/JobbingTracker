package com.delhomme.jobbingtrack.features.application.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationStatusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationStatusDao : BaseDao<ApplicationStatusEntity> {
    @Query("SELECT * FROM application_statuses WHERE isDeleted = 0")
    fun getAll(): Flow<List<ApplicationStatusEntity>>

    @Query("SELECT * FROM application_statuses WHERE id = :id AND isDeleted = 0 LIMIT 1")
    fun getById(id: String): Flow<ApplicationStatusEntity?>

    @Query("SELECT * FROM application_statuses WHERE userId = :userId AND isDeleted = 0")
    fun getAllForUser(userId: String): Flow<List<ApplicationStatusEntity>>

    @Query("""
        SELECT * FROM application_statuses 
        WHERE userId = :userId 
        AND updatedAt > :timestamp 
        AND (lastSyncAt IS NULL OR updatedAt > lastSyncAt)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<ApplicationStatusEntity>

    @Query("UPDATE application_statuses SET lastSyncAt = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM application_statuses WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): ApplicationStatusEntity?

    @Query("UPDATE application_statuses SET isDeleted = 1, deletedAt = :timestamp WHERE id = :id AND userId = :userId")
    override suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}