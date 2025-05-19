package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidatureDao {
    /** Toutes les acndidatures non supprimées **/
    @Query("SELECT * FROM candidatures WHERE isDeleted = 0")
    fun getAll(): Flow<List<CandidatureEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cand: CandidatureEntity)

    @Query("UPDATE candidatures SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("UPDATE candidatures SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)
}