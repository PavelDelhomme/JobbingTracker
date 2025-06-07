package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.FollowUpStatusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowUpStatusDao {
    @Query("SELECT * FROM follow_up_status WHERE isDeleted = 0")
    fun getAll(): Flow<List<FollowUpStatusEntity>>

    @Query("SELECT * FROM follow_up_status WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<FollowUpStatusEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: FollowUpStatusEntity)

    @Delete
    suspend fun delete(entity: FollowUpStatusEntity)
}
