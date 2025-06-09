package com.delhomme.jobbingtrack.companies.dao

import androidx.room.*
import com.delhomme.jobbingtrack.companies.entities.CompanyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao {
    @Query("SELECT * FROM companies WHERE userId = :userId ORDER BY createdAt DESC")
    fun getAllForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("""
      SELECT * FROM companies
       WHERE userId    = :userId
         AND isDeleted = 0
         AND isArchived= 0
      ORDER BY name
    """)
    fun getAllActiveForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("""
      SELECT * FROM companies
       WHERE userId    = :userId
         AND isArchived= 1
      ORDER BY name
    """)
    fun getArchivedForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("""
      SELECT * FROM companies
       WHERE userId    = :userId
         AND isDeleted = 1
      ORDER BY name
    """)
    fun getDeletedForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("SELECT * FROM companies WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<CompanyEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entreprise: CompanyEntity)

    @Update
    suspend fun update(entreprise: CompanyEntity)

    @Query("UPDATE companies SET isArchived = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String)

    @Query("UPDATE companies SET isDeleted  = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    @Query("UPDATE companies SET isDeleted  = 0 WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM companies WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM companies WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)
}
