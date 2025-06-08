package com.delhomme.jobbingtrack.data.local.dao.interviews

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.data.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.data.local.entities.InterviewContactCrossRef
import com.delhomme.jobbingtrack.data.local.entities.interview.InterviewEntity
import com.delhomme.jobbingtrack.data.local.entities.InterviewWithContacts
import kotlinx.coroutines.flow.Flow

@Dao
interface InterviewDao : DateRangeProvider<InterviewEntity> {
    override val tableName: String get() = "interviews"
    override val dateColumn: String get() = "dateTime"

    @Transaction
    @Query("SELECT * FROM interviews ORDER BY dateTime DESC")
    fun getAll(): Flow<List<InterviewEntity>>

    @Transaction
    @Query("""
      SELECT * FROM interviews
       WHERE userId    = :userId
         AND isDeleted = 0
         AND isArchived= 0
      ORDER BY dateTime DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<InterviewWithContacts>>

    @Transaction
    @Query("SELECT * FROM interviews WHERE userId = :userId AND isArchived = 0")
    fun getActiveWithContacts(userId: String): LiveData<List<InterviewWithContacts>>

    @Transaction
    @Query("""
      SELECT * FROM interviews
       WHERE userId    = :userId
         AND isArchived= 1
      ORDER BY dateTime DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<InterviewEntity>>


    @Transaction
    @Query("""
      SELECT * FROM interviews
       WHERE userId    = :userId
         AND isDeleted = 1
      ORDER BY dateTime DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<InterviewEntity>>

    @Transaction
    @Query("SELECT * FROM interviews WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<InterviewEntity?>

    // — Avec contacts liés —
    @Transaction
    @Query("SELECT * FROM interviews WHERE userId = :userId AND isDeleted = 0 ORDER BY dateTime DESC")
    fun getAllWithContactsForUser(userId: String): Flow<List<InterviewWithContacts>>

    @Transaction
    @Query("""
      SELECT * FROM interviews
       WHERE id         = :id
         AND userId    = :userId
         AND isDeleted = 0
         AND isArchived= 0
    """)
    fun getByIdActiveWithContacts(id: String, userId: String): Flow<InterviewWithContacts?>

    // — Mutations —
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entretien: InterviewEntity)

    @Update
    suspend fun update(entretien: InterviewEntity)

    @Query("DELETE FROM InterviewContactCrossRef WHERE interviewId = :interviewId")
    suspend fun clearContactsFor(interviewId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCrossRef(ref: InterviewContactCrossRef)

    @Query("UPDATE interviews SET isArchived = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String)

    @Query("UPDATE interviews SET isDeleted  = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    @Query("UPDATE interviews SET isDeleted  = 0 WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM interviews WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM interviews WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @RawQuery(observedEntities = [InterviewEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<InterviewEntity>>
}