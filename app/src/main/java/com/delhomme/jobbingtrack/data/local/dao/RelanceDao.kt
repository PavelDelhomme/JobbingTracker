package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RelanceDao {
    @Query("SELECT * FROM relances ORDER BY date DESC")
    fun getAll(): Flow<List<RelanceEntity>>

    @Query("SELECT * FROM relances WHERE isDeleted = 0 AND isArchived = 0 ORDER BY date DESC")
    fun getAllActive(): Flow<List<RelanceEntity>>

    @Query("SELECT * FROM relances WHERE isArchived = 1 ORDER BY date DESC")
    fun getAllArchived(): Flow<List<RelanceEntity>>

    @Query("SELECT * FROM relances WHERE isDeleted = 1 ORDER BY date DESC")
    fun getAllDeleted(): Flow<List<RelanceEntity>>

    @Query("SELECT * FROM relances WHERE id = :id")
    fun getById(id: String): Flow<RelanceEntity?>

    @Query("SELECT * FROM relances WHERE userId = :userId ORDER BY date DESC")
    fun getAllForUser(userId: String): Flow<List<RelanceEntity>>

    @Query("""
    SELECT * FROM relances
     WHERE userId = :userId
       AND isDeleted = 0
       AND isArchived = 0
    ORDER BY date DESC
  """)
    fun getActiveForUser(userId: String): Flow<List<RelanceEntity>>

    @Query("SELECT * FROM relances WHERE userId = :userId AND isArchived = 1 ORDER BY date DESC")
    fun getArchivedForUser(userId: String): Flow<List<RelanceEntity>>

    @Query("SELECT * FROM relances WHERE userId = :userId AND isDeleted = 1 ORDER BY date DESC")
    fun getDeletedForUser(userId: String): Flow<List<RelanceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(relance: RelanceEntity)

    @Query("UPDATE relances SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("UPDATE relances SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)
}
