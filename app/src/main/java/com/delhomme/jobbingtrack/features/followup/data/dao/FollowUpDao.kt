package com.delhomme.jobbingtrack.features.followup.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.core.common.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowUpDao : DateRangeProvider<FollowUpEntity>, BaseDao<FollowUpEntity> {
    override val tableName: String get() = "follow_ups"
    override val dateColumn: String get() = "date"

    @Query("SELECT * FROM follow_ups WHERE userId = :userId ORDER BY date DESC")
    fun getAllForUser(userId: String): Flow<List<FollowUpEntity>>

    @Transaction
    @Query("SELECT * FROM follow_ups WHERE id = :id")
    suspend fun getWithRelations(id: String): FollowUpWithRelations?

    @Query("""
        SELECT * FROM follow_ups
        WHERE userId = :userId
        AND isDeleted = 0
        AND isArchived = 0
        ORDER BY date DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<FollowUpEntity>>

    @Query("""
        SELECT * FROM follow_ups
        WHERE userId = :userId
        AND isArchived = 1
        ORDER BY date DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<FollowUpEntity>>

    @Query("""
        SELECT * FROM follow_ups
        WHERE userId = :userId
        AND isDeleted = 1
        ORDER BY date DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<FollowUpEntity>>

    @Query("SELECT * FROM follow_ups WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<FollowUpEntity?>

    @Query("SELECT * FROM follow_ups WHERE applicationId = :applicationId AND isDeleted = 0")
    fun getByApplicationId(applicationId: String): Flow<List<FollowUpEntity>>

    @Query("SELECT * FROM follow_ups WHERE companyId = :companyId AND isDeleted = 0")
    fun getByCompanyId(companyId: String): Flow<List<FollowUpEntity>>

    @Query("SELECT * FROM follow_ups WHERE contactId = :contactId AND isDeleted = 0")
    fun getByContactId(contactId: String): Flow<List<FollowUpEntity>>

    @Query("UPDATE follow_ups SET isArchived = 1, archivedAt = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE follow_ups SET isDeleted = 1, deletedAt = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE follow_ups SET isDeleted = 0, deletedAt = NULL WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM follow_ups WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM follow_ups WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @RawQuery(observedEntities = [FollowUpEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<FollowUpEntity>>

    @Transaction
    @Query("""
        SELECT * FROM follow_ups
        WHERE userId = :userId
        AND isDeleted = 0
        AND reminderDate BETWEEN :startDate AND :endDate
        ORDER BY reminderDate ASC
    """)
    fun getRemindersForDateRange(userId: String, startDate: Long, endDate: Long): Flow<List<FollowUpEntity>>

    @Query("""
        SELECT * FROM follow_ups 
        WHERE userId = :userId 
        AND updatedAt > :timestamp 
        AND (lastSyncAt IS NULL OR updatedAt > lastSyncAt)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<FollowUpEntity>

    @Query("UPDATE follow_ups SET lastSyncAt = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM follow_ups WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): FollowUpEntity?

    @Query("UPDATE follow_ups SET isDeleted = 1, deletedAt = :timestamp WHERE id = :id AND userId = :userId")
    override suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}