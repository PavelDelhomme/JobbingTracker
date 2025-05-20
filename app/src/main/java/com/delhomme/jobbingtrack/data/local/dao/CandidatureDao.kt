package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidatureDao {
    /** 1) Toutes les candidatures (tous statuts) **/
    @Query("SELECT * FROM candidatures ORDER BY applicationDate DESC")
    fun getAll(): Flow<List<CandidatureEntity>>

    /** 2) Candidatures actives (ni supprimées, ni archivées) **/
    @Query("""
        SELECT * FROM candidatures
         WHERE isDeleted = 0
           AND isArchived = 0
         ORDER BY applicationDate DESC
    """)
    fun getAllActive(): Flow<List<CandidatureEntity>>

    /** 3) Candidatures archivées **/
    @Query("SELECT * FROM candidatures WHERE isArchived = 1 ORDER BY applicationDate DESC")
    fun getAllArchived(): Flow<List<CandidatureEntity>>

    /** 4) Candidatures supprimées (corbeille) **/
    @Query("SELECT * FROM candidatures WHERE isDeleted = 1 ORDER BY applicationDate DESC")
    fun getAllDeleted(): Flow<List<CandidatureEntity>>

    /** 5) Détail d’une candidature par son ID **/
    @Query("SELECT * FROM candidatures WHERE id = :id")
    fun getById(id: String): Flow<CandidatureEntity?>

    /** — Insert ou mise à jour — **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cand: CandidatureEntity)

    /** — Actions d’archivage / suppression douce — **/
    @Query("UPDATE candidatures SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("UPDATE candidatures SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)
}