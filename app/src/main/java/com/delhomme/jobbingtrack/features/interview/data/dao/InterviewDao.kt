package com.delhomme.jobbingtrack.features.interview.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.core.common.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface InterviewDao : DateRangeProvider<InterviewEntity>, BaseDao<InterviewEntity> {
    override val tableName: String get() = "interviews"
    override val dateColumn: String get() = "dateTime"

    @Transaction
    @Query("SELECT * FROM interviews ORDER BY dateTime DESC")
    fun getAll(): Flow<List<InterviewEntity>>

    @Transaction
    @Query("SELECT * FROM interviews WHERE id = :id")
    suspend fun getWithRelations(id: String): InterviewWithRelations

    @Transaction
    @Query("""
      SELECT * FROM interviews
       WHERE userId = :userId
         AND is_deleted = 0
         AND is_archived = 0
      ORDER BY dateTime DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<InterviewWithContacts>>

    @Transaction
    @Query("SELECT * FROM interviews WHERE userId = :userId AND is_archived = 0")
    fun getActiveWithContacts(userId: String): LiveData<List<InterviewWithContacts>>

    @Transaction
    @Query("""
      SELECT * FROM interviews
       WHERE userId = :userId
         AND is_archived = 1
      ORDER BY dateTime DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<InterviewEntity>>

    @Transaction
    @Query("""
      SELECT * FROM interviews
       WHERE userId = :userId
         AND is_deleted = 1
      ORDER BY dateTime DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<InterviewEntity>>

    @Transaction
    @Query("SELECT * FROM interviews WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<InterviewEntity?>

    @Transaction
    @Query("SELECT * FROM interviews WHERE userId = :userId AND is_deleted = 0 ORDER BY dateTime DESC")
    fun getAllWithContactsForUser(userId: String): Flow<List<InterviewWithContacts>>

    @Transaction
    @Query("""
      SELECT * FROM interviews
       WHERE id = :id
         AND userId = :userId
         AND is_deleted = 0
         AND is_archived = 0
    """)
    fun getByIdActiveWithContacts(id: String, userId: String): Flow<InterviewWithContacts?>

    @Query("UPDATE interviews SET is_archived = 1, archived_at = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE interviews SET is_deleted = 1, deleted_at = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE interviews SET is_deleted = 0, deleted_at = NULL WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM interviews WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM interviews WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @RawQuery(observedEntities = [InterviewEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<InterviewEntity>>

    @Transaction
    @Query("SELECT * FROM interviews WHERE id = :id AND userId = :userId")
    fun getInterviewWithContacts(id: String, userId: String): Flow<InterviewWithContacts?>

    @Transaction
    @Query("""
      SELECT * FROM interviews
       WHERE userId = :userId
         AND is_deleted = 0
         AND is_archived = 0
      ORDER BY dateTime DESC
    """)
    fun getAllActiveWithContacts(userId: String): Flow<List<InterviewWithContacts>>

    @Query("""
        SELECT * FROM interviews 
        WHERE userId = :userId 
        AND updated_at > :timestamp 
        AND (last_sync_at IS NULL OR updated_at > last_sync_at)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<InterviewEntity>

    @Query("UPDATE interviews SET last_sync_at = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM interviews WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): InterviewEntity?

    @Query("UPDATE interviews SET is_deleted = 1, deleted_at = :timestamp WHERE id = :id AND userId = :userId")
    override suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}