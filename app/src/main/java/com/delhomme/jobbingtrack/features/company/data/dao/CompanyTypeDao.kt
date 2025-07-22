package com.delhomme.jobbingtrack.features.company.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyTypeDao : BaseDao<CompanyTypeEntity> {
    @Query("SELECT * FROM company_types WHERE is_deleted = 0")
    fun getAll(): Flow<List<CompanyTypeEntity>>

    @Query("SELECT * FROM company_types WHERE id = :id AND is_deleted = 0 LIMIT 1")
    fun getById(id: String): Flow<CompanyTypeEntity?>

    @Query("""
        SELECT * FROM company_types 
        WHERE userId = :userId 
        AND updated_at > :timestamp 
        AND (last_sync_at IS NULL OR updated_at > last_sync_at)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<CompanyTypeEntity>

    @Query("UPDATE company_types SET last_sync_at = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM company_types WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): CompanyTypeEntity?

    @Query("UPDATE company_types SET is_deleted = 1, deleted_at = :timestamp WHERE id = :id AND userId = :userId")
    suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}