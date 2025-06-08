package com.delhomme.jobbingtrack.followsup.dao


import androidx.room.*
import com.delhomme.jobbingtrack.followsup.entities.FollowUpPlateformEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowUpPlateformDao {
    @Query("SELECT * FROM follow_up_platforms WHERE isDeleted = 0")
    fun getAll(): Flow<List<FollowUpPlateformEntity>>

    @Query("SELECT * FROM follow_up_platforms WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<FollowUpPlateformEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: FollowUpPlateformEntity)

    @Delete
    suspend fun delete(entity: FollowUpPlateformEntity)
}
