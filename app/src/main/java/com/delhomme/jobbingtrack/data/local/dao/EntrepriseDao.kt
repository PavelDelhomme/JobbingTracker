package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntrepriseDao {
    @Query("SELECT * FROM companies ORDER BY name")
    fun getAll(): Flow<List<EntrepriseEntity>>

    @Query("""
    SELECT * FROM companies
     WHERE isArchived = 0
    ORDER BY name
  """)
    fun getAllActive(): Flow<List<EntrepriseEntity>>

    @Query("SELECT * FROM companies WHERE isArchived = 1 ORDER BY name")
    fun getAllArchived(): Flow<List<EntrepriseEntity>>

    /** Pas de champ `isDeleted` sur Entreprise ? sinon ajouter getAllDeleted() à la même enseigne */

    @Query("SELECT * FROM companies WHERE id = :id")
    fun getById(id: String): Flow<EntrepriseEntity?>

    @Query("""
    SELECT * FROM companies
     WHERE userId = :userId
    ORDER BY name
  """)
    fun getAllForUser(userId: String): Flow<List<EntrepriseEntity>>

    @Query("""
    SELECT * FROM companies
     WHERE userId = :userId
       AND isArchived = 0
    ORDER BY name
  """)
    fun getActiveForUser(userId: String): Flow<List<EntrepriseEntity>>

    @Query("SELECT * FROM companies WHERE userId = :userId AND isArchived = 1 ORDER BY name")
    fun getArchivedForUser(userId: String): Flow<List<EntrepriseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entreprise: EntrepriseEntity)

    @Query("UPDATE companies SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("DELETE FROM companies WHERE id = :id")
    suspend fun deleteById(id: String)
}
