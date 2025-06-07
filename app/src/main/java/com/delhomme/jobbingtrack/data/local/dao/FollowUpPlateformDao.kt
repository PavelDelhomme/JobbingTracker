package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowUpPlateformDao {
    @Query("SELECT * FROM follow_up_platforms WHERE isDeleted = 0")
    fun getAll(): Flow<List<FollowUpPlatformEntity>>

    @Query("SELECT * FROM follow_up_platforms WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<FollowUpPlatformEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: FollowUpPlatformEntity)

    @Delete
    suspend fun delete(entity: FollowUpPlatformEntity)
}
