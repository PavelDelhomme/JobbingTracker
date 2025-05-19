package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntrepriseDao {
    @Query("SELECT * FROM companies")
    fun getAll(): Flow<List<EntrepriseEntity>>

    @Query("SELECT * FROM companies WHERE id = :id")
    fun getById(id: String): Flow<EntrepriseEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entreprise: EntrepriseEntity)

    @Query("UPDATE companies SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("DELETE FROM companies WHERE id = :id")
    suspend fun deleteById(id: String)
}