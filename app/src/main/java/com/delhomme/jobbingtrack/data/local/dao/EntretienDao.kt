package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.EntretienContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.EntretienEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import kotlinx.coroutines.flow.Flow

@Dao
interface EntretienDao {
    @Transaction
    @Query("SELECT * FROM entretiens ORDER BY dateTime DESC")
    fun getAll(): Flow<List<EntretienEntity>>

    @Transaction
    @Query("""
      SELECT * FROM entretiens
       WHERE userId    = :userId
         AND isDeleted = 0
         AND isArchived= 0
      ORDER BY dateTime DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<EntretienEntity>>


    @Transaction
    @Query("""
      SELECT * FROM entretiens
       WHERE userId    = :userId
         AND isArchived= 1
      ORDER BY dateTime DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<EntretienEntity>>


    @Transaction
    @Query("""
      SELECT * FROM entretiens
       WHERE userId    = :userId
         AND isDeleted = 1
      ORDER BY dateTime DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<EntretienEntity>>

    @Transaction
    @Query("SELECT * FROM entretiens WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<EntretienEntity?>

    // — Avec contacts liés —
    @Transaction
    @Query("SELECT * FROM entretiens WHERE userId = :userId AND isDeleted = 0 ORDER BY dateTime DESC")
    fun getAllWithContactsForUser(userId: String): Flow<List<EntretienWithContacts>>

    @Transaction
    @Query("""
      SELECT * FROM entretiens
       WHERE id         = :id
         AND userId    = :userId
         AND isDeleted = 0
         AND isArchived= 0
    """)
    fun getByIdActiveWithContacts(id: String, userId: String): Flow<EntretienWithContacts?>

    // — Mutations —
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entretien: EntretienEntity)

    @Update
    suspend fun update(entretien: EntretienEntity)

    @Query("DELETE FROM EntretienContactCrossRef WHERE entretienId = :entretienId")
    suspend fun clearContactsFor(entretienId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCrossRef(ref: EntretienContactCrossRef)

    @Query("UPDATE entretiens SET isArchived = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String)

    @Query("UPDATE entretiens SET isDeleted  = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    @Query("UPDATE entretiens SET isDeleted  = 0 WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM entretiens WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM entretiens WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)
}