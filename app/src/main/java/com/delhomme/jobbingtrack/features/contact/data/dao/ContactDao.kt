package com.delhomme.jobbingtrack.features.contact.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactWithCompany
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao : BaseDao<ContactEntity> {
    @Transaction
    @Query("SELECT * FROM contacts WHERE id = :id")
    suspend fun getWithRelations(id: String): ContactWithRelations

    @Query("SELECT * FROM contacts WHERE userId = :userId ORDER BY created_at DESC")
    fun getAllForUser(userId: String): Flow<List<ContactEntity>>

    @Query("""
      SELECT * FROM contacts
       WHERE userId = :userId
         AND is_deleted = 0
         AND is_archived = 0
      ORDER BY lastName, firstName
    """)
    fun getAllActiveForUser(userId: String): Flow<List<ContactEntity>>

    @Query("""
      SELECT * FROM contacts
       WHERE userId = :userId
         AND is_archived = 1
      ORDER BY lastName, firstName
    """)
    fun getArchivedForUser(userId: String): Flow<List<ContactEntity>>

    @Query("""
      SELECT * FROM contacts
       WHERE userId = :userId
         AND is_deleted = 1
      ORDER BY lastName, firstName
    """)
    fun getDeletedForUser(userId: String): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<ContactEntity?>

    @Query("UPDATE contacts SET is_archived = 1, archived_at = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE contacts SET is_deleted = 1, deleted_at = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE contacts SET is_deleted = 0, deleted_at = NULL WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM contacts WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM contacts WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @Transaction
    @Query("SELECT * FROM contacts WHERE id = :id AND userId = :userId")
    fun getContactWithCompany(id: String, userId: String): Flow<ContactWithCompany?>

    @Transaction
    @Query("""
        SELECT * FROM contacts 
        WHERE userId = :userId AND is_deleted = 0 AND is_archived = 0
        ORDER BY lastName, firstName
    """)
    fun getAllActiveWithCompany(userId: String): Flow<List<ContactWithCompany>>

    @Query("""
        SELECT * FROM contacts 
        WHERE userId = :userId 
        AND updated_at > :timestamp 
        AND (last_sync_at IS NULL OR updated_at > last_sync_at)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<ContactEntity>

    @Query("UPDATE contacts SET last_sync_at = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM contacts WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): ContactEntity?

    @Query("UPDATE contacts SET is_deleted = 1, deleted_at = :timestamp WHERE id = :id AND userId = :userId")
    suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}