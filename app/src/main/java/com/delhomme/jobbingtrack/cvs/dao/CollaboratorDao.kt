package com.delhomme.jobbingtrack.cvs.dao

import androidx.room.*
import com.delhomme.jobbingtrack.cvs.entities.CollaboratorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollaboratorDao {
    @Query("SELECT * FROM collaborators WHERE isDeleted = 0")
    fun getAll(): Flow<List<CollaboratorEntity>>

    @Query("SELECT * FROM collaborators WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<CollaboratorEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: CollaboratorEntity)

    @Update
    suspend fun update(entity: CollaboratorEntity)

    @Delete
    suspend fun delete(entity: CollaboratorEntity)
}
