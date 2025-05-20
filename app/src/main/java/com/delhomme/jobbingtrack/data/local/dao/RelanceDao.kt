package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RelanceDao {
    @Query("SELECT * FROM relances WHERE isDeleted = 0")
    fun getAll(): Flow<List<RelanceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(relance: RelanceEntity)

    @Query("UPDATE relances SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("UPDATE relances SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)
}