package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.CallTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CallTypeDao {
    @Query("SELECT * FROM call_types WHERE isDeleted = 0")
    fun getAll(): Flow<List<CallTypeEntity>>

    @Query("SELECT * FROM call_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<CallTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: CallTypeEntity)

    @Delete
    suspend fun delete(entity: CallTypeEntity)
}
