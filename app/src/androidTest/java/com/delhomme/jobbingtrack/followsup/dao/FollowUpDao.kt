package com.delhomme.jobbingtrack.followsup.dao


import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.commons.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.followsup.entities.FollowUpEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowUpDao : DateRangeProvider<FollowUpEntity> {
    override val tableName: String get() = "followups"
    override val dateColumn: String get() = "date"

    /** 1) Tout pour cet user */
    @Query("SELECT * FROM followups WHERE userId = :userId ORDER BY date DESC")
    fun getAllForUser(userId: String): Flow<List<FollowUpEntity>>

    /** 2) Actifs */
    @Query("""
      SELECT * FROM followups
       WHERE userId=:userId AND isDeleted=0 AND isArchived=0
       ORDER BY date DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<FollowUpEntity>>

    /** 3) Archivés */
    @Query("""
      SELECT * FROM followups
       WHERE userId=:userId AND isArchived=1 AND isDeleted=0
       ORDER BY date DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<FollowUpEntity>>

    /** 4) Supprimés (corbeille) */
    @Query("""
      SELECT * FROM followups
       WHERE userId=:userId AND isDeleted=1
       ORDER BY date DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<FollowUpEntity>>

    /** 5) Détail */
    @Query("SELECT * FROM followups WHERE id=:id AND userId=:userId")
    fun getByIdForUser(id: String, userId: String): Flow<FollowUpEntity?>

    /** Insert ou replace */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(relance: FollowUpEntity)

    /** Mise à jour (tous champs) */
    @Update
    suspend fun update(relance: FollowUpEntity)

    /** Batch archive */
    @Query("UPDATE followups SET isArchived=1 WHERE id IN(:ids) AND userId=:userId")
    suspend fun archive(ids: List<String>, userId: String)

    /** Batch soft-delete */
    @Query("UPDATE followups SET isDeleted=1 WHERE id IN(:ids) AND userId=:userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    /** Batch restore */
    @Query("UPDATE followups SET isDeleted=0 WHERE id IN(:ids) AND userId=:userId")
    suspend fun restore(ids: List<String>, userId: String)

    /** Batch delete forever */
    @Query("DELETE FROM followups WHERE id IN(:ids) AND userId=:userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    /** Tout vider pour l'user */
    @Query("DELETE FROM followups WHERE userId=:userId")
    suspend fun deleteAllForUser(userId: String)

    /** Tout vider pour cet entreprise **/
    @Query("DELETE FROM followups WHERE companyId=:companyId")
    suspend fun deleteAllForCompany(companyId: String)

    /** Tout vider pour cet candidature **/
    @Query("DELETE FROM followups WHERE applicationId=:applicationId")
    suspend fun deleteAllForApplication(applicationId: String)

    /** Tout vider pour ce contact **/
    @Query("DELETE FROM followups WHERE contactId=:contactId")
    suspend fun deleteAllForContact(contactId: String)

    @RawQuery(observedEntities = [FollowUpEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<FollowUpEntity>>
}
