package com.delhomme.jobbingtrack.features.cvs.data.sources.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.delhomme.jobbingtrack.features.cvs.data.entities.CollaboratorEntity


@Dao
interface CollaboratorDao {
    @Query("SELECT * FROM collaborators WHERE is_deleted = 0")
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