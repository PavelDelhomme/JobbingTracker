package com.delhomme.jobbingtrack.features.contact.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao : BaseDao<ContactEntity> {
    @Query("SELECT * FROM contacts WHERE userId = :userId ORDER BY lastName, firstName")
    fun getAllForUser(userId: String): Flow<List<ContactEntity>>

    @Transaction
    @Query("SELECT * FROM contacts WHERE id = :id")
    suspend fun getWithRelations(id: String): ContactWithRelations?

    @Query("""
        SELECT * FROM contacts
        WHERE userId = :userId
        AND isDeleted = 0
        AND isArchived = 0
        ORDER BY lastName, firstName
    """)
    fun getAllActiveForUser(userId: String): Flow<List<ContactEntity>>

    @Query("""
        SELECT * FROM contacts
        WHERE userId = :userId
        AND isArchived = 1
        ORDER BY lastName, firstName
    """)
    fun getArchivedForUser(userId: String): Flow<List<ContactEntity>>

    @Query("""
        SELECT * FROM contacts
        WHERE userId = :userId
        AND isDeleted = 1
        ORDER BY lastName, firstName
    """)
    fun getDeletedForUser(userId: String): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<ContactEntity?>

    @Transaction
    @Query("SELECT * FROM contacts WHERE id = :id AND userId = :userId")
    fun getContactWithRelations(id: String, userId: String): Flow<ContactWithRelations?>

    @Query("""
        SELECT * FROM contacts
        WHERE userId = :userId
        AND companyId = :companyId
        AND isDeleted = 0
        ORDER BY lastName, firstName
    """)
    fun getByCompanyId(userId: String, companyId: String): Flow<List<ContactEntity>>

    @Query("UPDATE contacts SET isArchived = 1, archivedAt = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE contacts SET isDeleted = 1, deletedAt = :timestamp WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE contacts SET isDeleted = 0, deletedAt = NULL WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM contacts WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM contacts WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @Query("""
        SELECT * FROM contacts 
        WHERE userId = :userId 
        AND updatedAt > :timestamp 
        AND (lastSyncAt IS NULL OR updatedAt > lastSyncAt)
    """)
    override suspend fun getUpdatedSince(timestamp: Long, userId: String): List<ContactEntity>

    @Query("UPDATE contacts SET lastSyncAt = :syncTime WHERE id IN (:ids)")
    override suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long)

    @Query("SELECT * FROM contacts WHERE id = :id AND userId = :userId LIMIT 1")
    override suspend fun getById(id: String, userId: String): ContactEntity?

    @Query("UPDATE contacts SET isDeleted = 1, deletedAt = :timestamp WHERE id = :id AND userId = :userId")
    override suspend fun softDeleteById(id: String, userId: String, timestamp: Long): Int
}