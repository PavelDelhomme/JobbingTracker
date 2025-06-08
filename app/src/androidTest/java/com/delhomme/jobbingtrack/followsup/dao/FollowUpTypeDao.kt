package com.delhomme.jobbingtrack.followsup.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.followup.FollowUpTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowUpTypeDao {
    @Query("SELECT * FROM follow_up_types WHERE isDeleted = 0")
    fun getAll(): Flow<List<FollowUpTypeEntity>>

    @Query("SELECT * FROM follow_up_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<FollowUpTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: FollowUpTypeEntity)

    @Delete
    suspend fun delete(entity: FollowUpTypeEntity)
}
