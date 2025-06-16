package com.delhomme.jobbingtrack.datas.daos.followsups

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpStatusEntity
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
