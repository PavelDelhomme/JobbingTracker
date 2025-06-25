package com.delhomme.jobbingtrack.applications.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Junction
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.applications.ApplicationEntity
import com.delhomme.jobbingtrack.calls.CallEntity
import com.delhomme.jobbingtrack.commons.entities.ApplicationCallCrossRef
import com.delhomme.jobbingtrack.commons.entities.ApplicationContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.ApplicationFollowUpCrossRef
import com.delhomme.jobbingtrack.commons.entities.ApplicationInterviewCrossRef
import com.delhomme.jobbingtrack.commons.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.contacts.ContactEntity
import com.delhomme.jobbingtrack.followsup.FollowUpEntity
import com.delhomme.jobbingtrack.interviews.InterviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao : DateRangeProvider<ApplicationEntity> {
    override val tableName: String get() = "applications"
    override val dateColumn: String get() = "applicationDate"

    @Query("SELECT * FROM applications WHERE userId = :userId ORDER BY applicationDate DESC")
    fun getAllForUser(userId: String): Flow<List<ApplicationEntity>>

    @Query("""
      SELECT * FROM applications
       WHERE userId    = :userId
         AND isDeleted = 0
         AND isArchived= 0
      ORDER BY applicationDate DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<ApplicationEntity>>

    @Query("""
      SELECT * FROM applications
       WHERE userId    = :userId
         AND isArchived= 1
      ORDER BY applicationDate DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<ApplicationEntity>>

    @Query("""
      SELECT * FROM applications
       WHERE userId    = :userId
         AND isDeleted = 1
      ORDER BY applicationDate DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<ApplicationEntity>>

    @Query("SELECT * FROM applications WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<ApplicationEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cand: ApplicationEntity)

    @Update
    suspend fun update(cand: ApplicationEntity)

    @Query("UPDATE applications SET isArchived = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String)

    @Query("UPDATE applications SET isDeleted  = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    @Query("UPDATE applications SET isDeleted  = 0 WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM applications WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM applications WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @RawQuery(observedEntities = [ApplicationEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<ApplicationEntity>>

    @Transaction
    @Query("SELECT * FROM applications WHERE id = :id AND userId = :userId")
    fun getApplicationWithContacts(id: String, userId: String): Flow<ApplicationWithContacts?>


    @Transaction
    @Query("SELECT * FROM applications WHERE id = :id AND userId = :userId")
    fun getApplicationWithCalls(id: String, userId: String): Flow<ApplicationWithCalls?>

    @Transaction
    @Query("SELECT * FROM applications WHERE id = :id AND userId = :userId")
    fun getApplicationWithFollowUps(id: String, userId: String): Flow<ApplicationWithFollowUps?>

    @Transaction
    @Query("SELECT * FROM applications WHERE id = :id AND userId = :userId")
    fun getApplicationWithInterviews(id: String, userId: String): Flow<ApplicationWithInterviews?>

    @Transaction
    @Query("SELECT * FROM applications WHERE id = :id AND userId = :userId")
    fun getApplicationFull(id: String, userId: String): Flow<ApplicationFull?>

    @Transaction
    @Query("""
        SELECT * FROM applications 
        WHERE userId = :userId AND isDeleted = 0 AND isArchived = 0
        ORDER BY applicationDate DESC
    """)
    fun getAllActiveWithContacts(userId: String): Flow<List<ApplicationWithContacts>>

    // === GESTION DES CROSSREF ===
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApplicationContactCrossRef(crossRef: ApplicationContactCrossRef)

    @Delete
    suspend fun deleteApplicationContactCrossRef(crossRef: ApplicationContactCrossRef)

    @Query("DELETE FROM ApplicationContactCrossRef WHERE applicationId = :applicationId")
    suspend fun clearContactsForApplication(applicationId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApplicationCallCrossRef(crossRef: ApplicationCallCrossRef)

    @Query("DELETE FROM ApplicationCallCrossRef WHERE applicationId = :applicationId")
    suspend fun clearCallsForApplication(applicationId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApplicationFollowUpCrossRef(crossRef: ApplicationFollowUpCrossRef)

    @Query("DELETE FROM ApplicationFollowUpCrossRef WHERE applicationId = :applicationId")
    suspend fun clearFollowUpsForApplication(applicationId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApplicationInterviewCrossRef(crossRef: ApplicationInterviewCrossRef)

    @Query("DELETE FROM ApplicationInterviewCrossRef WHERE applicationId = :applicationId")
    suspend fun clearInterviewsForApplication(applicationId: String)

}
