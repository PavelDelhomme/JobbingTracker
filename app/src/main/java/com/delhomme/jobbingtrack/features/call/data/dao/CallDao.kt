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
    override val dateColumn: String get() = "dateTime"

    @Query("SELECT * FROM calls WHERE userId = :userId ORDER BY dateTime DESC")
    fun getAllForUser(userId: String): Flow<List<CallEntity>>

    @Query("""
      SELECT * FROM calls
       WHERE userId = :userId
         AND is_deleted = 0
         AND is_archived = 0
      ORDER BY dateTime DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<CallEntity>>

    @Query("""
      SELECT * FROM calls
       WHERE userId = :userId
         AND is_archived = 1
      ORDER BY dateTime DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<CallEntity>>

    @Query("""
      SELECT * FROM calls
       WHERE userId = :userId
         AND is_deleted = 1
      ORDER BY dateTime DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<CallEntity>>

    @Query("SELECT * FROM calls WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<CallEntity?>

    @Query("UPDATE calls SET is_archived = 1, archived_at = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE calls SET is_deleted = 1, deleted_at = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE calls SET is_deleted = 0, deleted_at = NULL WHERE id IN(:ids) AND user_id = :userId")
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
    @Query("""
        SELECT * FROM calls 
        WHERE userId = :userId AND is_deleted = 0 AND is_archived = 0
        ORDER BY dateTime DESC
    """)
    fun getAllActiveWithContacts(userId: String): Flow<List<CallWithContacts>>

    @Query("""
        SELECT * FROM calls 
        WHERE userId = :userId 
        AND updated_at > :timestamp 
        AND (last_sync_at IS NULL OR updated_at > last_sync_at)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<CallEntity>

    @Query("UPDATE calls SET last_sync_at = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM calls WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): CallEntity?

    @Query("UPDATE calls SET is_deleted = 1, deleted_at = :timestamp WHERE id = :id AND userId = :userId")
    suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}