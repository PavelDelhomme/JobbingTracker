package com.delhomme.jobbingtrack.features.followup.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.core.common.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpContactCrossRef
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpStatusEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpTypeEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpWithContacts
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowUpDao : DateRangeProvider<FollowUpEntity>, BaseDao<FollowUpEntity> {
    override val tableName: String get() = "followups"
    override val dateColumn: String get() = "date"

    @Query("SELECT * FROM followups WHERE userId = :userId ORDER BY date DESC")
    fun getAllForUser(userId: String): Flow<List<FollowUpEntity>>

    @Query("""
      SELECT * FROM followups
       WHERE userId = :userId AND is_deleted = 0 AND is_archived = 0
       ORDER BY date DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<FollowUpEntity>>

    @Query("""
      SELECT * FROM followups
       WHERE userId = :userId AND is_archived = 1 AND is_deleted = 0
       ORDER BY date DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<FollowUpEntity>>

    @Query("""
      SELECT * FROM followups
       WHERE userId = :userId AND is_deleted = 1
       ORDER BY date DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<FollowUpEntity>>

    @Query("SELECT * FROM followups WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<FollowUpEntity?>

    @Query("UPDATE followups SET is_archived = 1, archived_at = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE followups SET is_deleted = 1, deleted_at = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE followups SET is_deleted = 0, deleted_at = NULL WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM followups WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM followups WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @Query("DELETE FROM followups WHERE companyId = :companyId")
    suspend fun deleteAllForCompany(companyId: String)

    @Query("DELETE FROM followups WHERE applicationId = :applicationId")
    suspend fun deleteAllForApplication(applicationId: String)

    @RawQuery(observedEntities = [FollowUpEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<FollowUpEntity>>

    @Transaction
    @Query("SELECT * FROM followups WHERE id = :id AND userId = :userId")
    fun getFollowUpWithContacts(id: String, userId: String): Flow<FollowUpWithContacts?>

    @Transaction
    @Query("""
        SELECT * FROM followups 
        WHERE userId = :userId AND is_deleted = 0 AND is_archived = 0
        ORDER BY date DESC
    """)
    fun getAllActiveWithContacts(userId: String): Flow<List<FollowUpWithContacts>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFollowUpContactCrossRef(crossRef: FollowUpContactCrossRef)

    @Query("DELETE FROM FollowUpContactCrossRef WHERE followUpId = :followUpId")
    suspend fun clearContactsForFollowUp(followUpId: String)

    @Query("SELECT contactId FROM FollowUpContactCrossRef WHERE followUpId = :followUpId")
    suspend fun getContactIdsForFollowUp(followUpId: String): List<String>

    @Query("SELECT * FROM follow_up_types WHERE isDeleted = 0")
    fun getAllFollowUpTypes(): Flow<List<FollowUpTypeEntity>>

    @Query("SELECT * FROM follow_up_status WHERE is_deleted = 0")
    fun getAllFollowUpStatuses(): Flow<List<FollowUpStatusEntity>>

    @Query("""
        SELECT * FROM followups 
        WHERE userId = :userId 
        AND updated_at > :timestamp 
        AND (last_sync_at IS NULL OR updated_at > last_sync_at)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<FollowUpEntity>

    @Query("UPDATE followups SET last_sync_at = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM followups WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): FollowUpEntity?

    @Query("UPDATE followups SET is_deleted = 1, deleted_at = :timestamp WHERE id = :id AND userId = :userId")
    suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}