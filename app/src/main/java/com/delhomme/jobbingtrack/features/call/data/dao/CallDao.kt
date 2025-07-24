package com.delhomme.jobbingtrack.features.call.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.core.common.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallWithContacts
import kotlinx.coroutines.flow.Flow

@Dao
interface CallDao : DateRangeProvider<CallEntity>, BaseDao<CallEntity> {
    override val tableName: String get() = "calls"
    override val dateColumn: String get() = "timestamp"

    @Query("SELECT * FROM calls WHERE userId = :userId ORDER BY timestamp DESC")
    fun getAllForUser(userId: String): Flow<List<CallEntity>>

    @Query(
        """
      SELECT * FROM calls
       WHERE userId = :userId
         AND isDeleted = 0
         AND isArchived = 0
      ORDER BY timestamp DESC
    """
    )
    fun getAllActiveForUser(userId: String): Flow<List<CallEntity>>

    @Query(
        """
      SELECT * FROM calls
       WHERE userId = :userId
         AND isArchived = 1
      ORDER BY timestamp DESC
    """
    )
    fun getArchivedForUser(userId: String): Flow<List<CallEntity>>

    @Query(
        """
      SELECT * FROM calls
       WHERE userId = :userId
         AND isDeleted = 1
      ORDER BY timestamp DESC
    """
    )
    fun getDeletedForUser(userId: String): Flow<List<CallEntity>>

    @Query("SELECT * FROM calls WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<CallEntity?>

    @Query("UPDATE calls SET isArchived = 1, archivedAt = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE calls SET isDeleted = 1, deletedAt = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE calls SET isDeleted = 0, deletedAt = NULL WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM calls WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM calls WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @RawQuery(observedEntities = [CallEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<CallEntity>>

    @Transaction
    @Query("SELECT * FROM calls WHERE id = :id AND userId = :userId")
    fun getCallWithContacts(id: String, userId: String): Flow<CallWithContacts?>

    @Transaction
    @Query(
        """
        SELECT * FROM calls 
        WHERE userId = :userId AND isDeleted = 0 AND isArchived = 0
        ORDER BY timestamp DESC
    """
    )
    fun getAllActiveWithContacts(userId: String): Flow<List<CallWithContacts>>

    @Query("""
        SELECT * FROM calls 
        WHERE userId = :userId 
        AND updatedAt > :timestamp 
        AND (lastSyncAt IS NULL OR updatedAt > lastSyncAt)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<CallEntity>

    @Query("UPDATE calls SET lastSyncAt = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM calls WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): CallEntity?

    @Query("UPDATE calls SET isDeleted = 1, deletedAt = :timestamp WHERE id = :id AND userId = :userId")
    override suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}