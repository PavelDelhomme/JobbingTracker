package com.delhomme.jobbingtrack.features.company.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao : BaseDao<CompanyEntity> {
    @Query("SELECT * FROM companies WHERE userId = :userId ORDER BY created_at DESC")
    fun getAllForUser(userId: String): Flow<List<CompanyEntity>>

    @Transaction
    @Query("SELECT * FROM companies WHERE id = :id")
    suspend fun getWithRelations(id: String): CompanyWithRelations

    @Query("""
      SELECT * FROM companies
       WHERE userId = :userId
         AND is_deleted = 0
         AND is_archived = 0
      ORDER BY name
    """)
    fun getAllActiveForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("""
      SELECT * FROM companies
       WHERE userId = :userId
         AND is_archived = 1
      ORDER BY name
    """)
    fun getArchivedForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("""
      SELECT * FROM companies
       WHERE userId = :userId
         AND is_deleted = 1
      ORDER BY name
    """)
    fun getDeletedForUser(userId: String): Flow<List<CompanyEntity>>

    @Query("SELECT * FROM companies WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<CompanyEntity?>

    @Query("UPDATE companies SET is_archived = 1, archived_at = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE companies SET is_deleted = 1, deleted_at = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE companies SET is_deleted = 0, deleted_at = NULL WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM companies WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM companies WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @Query("""
        SELECT * FROM companies 
        WHERE userId = :userId 
        AND updated_at > :timestamp 
        AND (last_sync_at IS NULL OR updated_at > last_sync_at)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<CompanyEntity>

    @Query("UPDATE companies SET last_sync_at = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM companies WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): CompanyEntity?

    @Query("UPDATE companies SET is_deleted = 1, deleted_at = :timestamp WHERE id = :id AND userId = :userId")
    suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}