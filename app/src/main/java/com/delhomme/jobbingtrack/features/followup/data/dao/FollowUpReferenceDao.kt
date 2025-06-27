package com.delhomme.jobbingtrack.features.followup.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
