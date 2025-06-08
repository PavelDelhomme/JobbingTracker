package com.delhomme.jobbingtrack.contacts.dao

import androidx.room.*
import com.delhomme.jobbingtrack.contacts.entities.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts WHERE userId = :userId ORDER BY createdAt DESC")
    fun getAllForUser(userId: String): Flow<List<ContactEntity>>

    @Query("""
      SELECT * FROM contacts
       WHERE userId    = :userId
         AND isDeleted = 0
         AND isArchived= 0
      ORDER BY lastName, firstName
    """)
    fun getAllActiveForUser(userId: String): Flow<List<ContactEntity>>

    @Query("""
      SELECT * FROM contacts
       WHERE userId    = :userId
         AND isArchived= 1
      ORDER BY lastName, firstName
    """)
    fun getArchivedForUser(userId: String): Flow<List<ContactEntity>>

    @Query("""
      SELECT * FROM contacts
       WHERE userId    = :userId
         AND isDeleted = 1
      ORDER BY lastName, firstName
    """)
    fun getDeletedForUser(userId: String): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<ContactEntity?>

    // — Mutations —
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(contact: ContactEntity)

    @Update
    suspend fun update(contact: ContactEntity)

    @Query("UPDATE contacts SET isArchived = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String)

    @Query("UPDATE contacts SET isDeleted  = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    @Query("UPDATE contacts SET isDeleted  = 0 WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM contacts WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM contacts WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)
}
