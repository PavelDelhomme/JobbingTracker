package com.delhomme.jobbingtrack.features.followup.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpPlatformEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowUpPlatformDao : BaseDao<FollowUpPlatformEntity> {
    @Query("SELECT * FROM follow_up_platforms WHERE isDeleted = 0")
    fun getAll(): Flow<List<FollowUpPlatformEntity>>

    @Query("SELECT * FROM follow_up_platforms WHERE id = :id AND isDeleted = 0 LIMIT 1")
    fun getById(id: String): Flow<FollowUpPlatformEntity?>

    @Query("SELECT * FROM follow_up_platforms WHERE userId = :userId AND isDeleted = 0")
    fun getAllForUser(userId: String): Flow<List<FollowUpPlatformEntity>>

    @Query("""
        SELECT * FROM follow_up_platforms 
        WHERE userId = :userId 
        AND updatedAt > :timestamp 
        AND (lastSyncAt IS NULL OR updatedAt > lastSyncAt)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<FollowUpPlatformEntity>

    @Query("UPDATE follow_up_platforms SET lastSyncAt = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM follow_up_platforms WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): FollowUpPlatformEntity?

    @Query("UPDATE follow_up_platforms SET isDeleted = 1, deletedAt = :timestamp WHERE id = :id AND userId = :userId")
    override suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}