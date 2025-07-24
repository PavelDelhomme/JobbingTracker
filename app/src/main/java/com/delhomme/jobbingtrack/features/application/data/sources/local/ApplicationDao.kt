package com.delhomme.jobbingtrack.features.application.data.sources.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao : BaseDao<ApplicationEntity> {
    // Récupération avec relations
    @Transaction
    @Query("SELECT * FROM applications WHERE id = :id")
    suspend fun getWithRelations(id: String): ApplicationWithRelations

    @Query("SELECT * FROM applications WHERE userId = :userId ORDER BY applicationDate DESC")
    fun getAllForUser(userId: String): Flow<List<ApplicationEntity>>

    @Query("""
      SELECT * FROM applications
       WHERE userId = :userId
         AND isDeleted = 0
         AND isArchived = 0
      ORDER BY applicationDate DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<ApplicationEntity>>

    @Query("""
      SELECT * FROM applications
       WHERE userId = :userId
         AND isArchived = 1
      ORDER BY applicationDate DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<ApplicationEntity>>

    @Query("""
      SELECT * FROM applications
       WHERE userId = :userId
         AND isDeleted = 1
      ORDER BY applicationDate DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<ApplicationEntity>>

    @Query("SELECT * FROM applications WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<ApplicationEntity?>

    @Query("UPDATE applications SET isArchived = 1, archivedAt = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE applications SET isDeleted = 1, deletedAt = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE applications SET isDeleted = 0, deletedAt = NULL WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM applications WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM applications WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @Query("SELECT * FROM applications WHERE userId = :userId AND applicationDate BETWEEN :from AND :to ORDER BY applicationDate DESC")
    fun getByDateRangeForUser(userId: String, from: Long, to: Long): Flow<List<ApplicationEntity>>

    @Query("""
        SELECT * FROM applications 
        WHERE userId = :userId 
        AND updatedAt > :timestamp 
        AND (lastSyncAt IS NULL OR updatedAt > lastSyncAt)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<ApplicationEntity>

    @Query("UPDATE applications SET lastSyncAt = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)
}