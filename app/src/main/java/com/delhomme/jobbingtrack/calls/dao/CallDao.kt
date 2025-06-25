package com.delhomme.jobbingtrack.calls.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Junction
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Relation
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.calls.Call
import com.delhomme.jobbingtrack.calls.CallEntity
import com.delhomme.jobbingtrack.calls.CallWithContacts
import com.delhomme.jobbingtrack.commons.entities.CallContactCrossRef
import com.delhomme.jobbingtrack.commons.interfaces.DateRangeProvider
import kotlinx.coroutines.flow.Flow


@Dao
interface CallDao : DateRangeProvider<CallEntity> {
    override val tableName: String get() = "calls"
    override val dateColumn: String get() = "date"

    @Query("SELECT * FROM calls WHERE userId = :userId ORDER BY dateTime DESC")
    fun getAllForUser(userId: String): Flow<List<CallEntity>>

    @Query("""
    SELECT * FROM calls
     WHERE userId    = :userId
       AND isDeleted = 0
       AND isArchived= 0
    ORDER BY dateTime DESC
  """)
    fun getAllActiveForUser(userId: String): Flow<List<CallEntity>>

    @Query("""
    SELECT * FROM calls
     WHERE userId    = :userId
       AND isArchived= 1
    ORDER BY dateTime DESC
  """)
    fun getArchivedForUser(userId: String): Flow<List<CallEntity>>

    @Query("""
    SELECT * FROM calls
     WHERE userId    = :userId
       AND isDeleted = 1
    ORDER BY dateTime DESC
  """)
    fun getDeletedForUser(userId: String): Flow<List<CallEntity>>

    @Query("SELECT * FROM calls WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<CallEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(appel: CallEntity)

    @Query("UPDATE calls SET isArchived = 1    WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String)

    @Query("UPDATE calls SET isDeleted  = 1    WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    @Query("UPDATE calls SET isDeleted  = 0    WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE   FROM calls            WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE   FROM calls            WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @RawQuery(observedEntities = [CallEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<CallEntity>>

    @Transaction
    @Query("SELECT * FROM calls WHERE id = :id AND userId = :userId")
    fun getCallWithContacts(id: String, userId: String): Flow<CallWithContacts?>


    @Transaction
    @Query("SELECT * FROM calls WHERE id = :id AND userId = :userId")
    fun getCallFull(id: String, userId: String): Flow<Call?>

    @Transaction
    @Query("""
        SELECT * FROM calls 
        WHERE userId = :userId AND isDeleted = 0 AND isArchived = 0
        ORDER BY dateTime DESC
    """)
    fun getAllActiveWithContacts(userId: String): Flow<List<CallWithContacts>>

    // === GESTION DES CROSSREF ===
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCallContactCrossRef(crossRef: CallContactCrossRef)

    @Query("DELETE FROM CallContactCrossRef WHERE callId = :callId")
    suspend fun clearContactsForCall(callId: String)

}
