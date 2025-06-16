package com.delhomme.jobbingtrack.datas.daos.calls

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.datas.entities.calls.CallTypeEntity
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
