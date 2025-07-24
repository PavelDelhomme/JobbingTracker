package com.delhomme.jobbingtrack.features.contact.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.contact.data.entities.PositionTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PositionTypeDao : BaseDao<PositionTypeEntity> {
    @Query("SELECT * FROM position_types WHERE is_deleted = 0")
    fun getAll(): Flow<List<PositionTypeEntity>>

    @Query("SELECT * FROM position_types WHERE id = :id AND is_deleted = 0 LIMIT 1")
    fun getById(id: String): Flow<PositionTypeEntity?>

    @Query("SELECT * FROM position_types WHERE userId = :userId AND is_deleted = 0")
    fun getAllForUser(userId: String): Flow<List<PositionTypeEntity>>

    @Query("""
        SELECT * FROM position_types 
        WHERE userId = :userId 
        AND updated_at > :timestamp 
        AND (last_sync_at IS NULL OR updated_at > last_sync_at)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<PositionTypeEntity>

    @Query("UPDATE position_types SET last_sync_at = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM position_types WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): PositionTypeEntity?

    @Query("UPDATE position_types SET is_deleted = 1, deleted_at = :timestamp WHERE id = :id AND userId = :userId")
    suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}