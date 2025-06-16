package com.delhomme.jobbingtrack.datas.daos.followsups

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpPlateformEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FollowUpPlatformDao {
    @Query("SELECT * FROM follow_up_platforms WHERE isDeleted = 0")
    fun getAll(): Flow<List<FollowUpPlateformEntity>>

    @Query("SELECT * FROM follow_up_platforms WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<FollowUpPlateformEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: FollowUpPlateformEntity)

    @Delete
    suspend fun delete(entity: FollowUpPlateformEntity)
}
