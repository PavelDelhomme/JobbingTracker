package com.delhomme.jobbingtrack.data.local.dao


import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    /** 1) Tous les contacts (tous statuts) **/
    @Query("SELECT * FROM contacts ORDER BY lastName, firstName")
    fun getAll(): Flow<List<ContactEntity>>

    /** 2) Tous les contacts actifs (ni supprimés ni archivés) **/
    @Query("""
    SELECT * FROM contacts
     WHERE isDeleted = 0
       AND isArchived = 0
    ORDER BY lastName, firstName
  """)
    fun getAllActive(): Flow<List<ContactEntity>>

    /** 3) Tous les contacts archivés **/
    @Query("SELECT * FROM contacts WHERE isArchived = 1 ORDER BY lastName, firstName")
    fun getAllArchived(): Flow<List<ContactEntity>>

    /** 4) Tous les contacts supprimés **/
    @Query("SELECT * FROM contacts WHERE isDeleted = 1 ORDER BY lastName, firstName")
    fun getAllDeleted(): Flow<List<ContactEntity>>

    /** 5) Détail par ID **/
    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getById(id: String): Flow<ContactEntity?>

    /** 6) Même choses mais pour un seul userId **/
    @Query("SELECT * FROM contacts WHERE userId = :userId ORDER BY lastName, firstName")
    fun getAllForUser(userId: String): Flow<List<ContactEntity>>

    @Query("""
    SELECT * FROM contacts
     WHERE userId = :userId
       AND isDeleted = 0
       AND isArchived = 0
    ORDER BY lastName, firstName
  """)
    fun getActiveForUser(userId: String): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE userId = :userId AND isArchived = 1 ORDER BY lastName, firstName")
    fun getArchivedForUser(userId: String): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE userId = :userId AND isDeleted = 1 ORDER BY lastName, firstName")
    fun getDeletedForUser(userId: String): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<ContactEntity?>

    /** Inserts / updates **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: ContactEntity)

    /** Archivage / suppression douce **/
    @Query("UPDATE contacts SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("UPDATE contacts SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)
}
