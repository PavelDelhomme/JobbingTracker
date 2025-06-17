package com.delhomme.jobbingtrack.datas.daos.followsups

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
import com.delhomme.jobbingtrack.commons.entities.FollowUpCallCrossRef
import com.delhomme.jobbingtrack.commons.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpContactCrossRef
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity
import kotlinx.coroutines.flow.Flow


data class FollowUpWithContacts(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            FollowUpContactCrossRef::class,
            parentColumn = "followUpId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)


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

    @RawQuery(observedEntities = [FollowUpEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<FollowUpEntity>>

    // === NOUVELLES MÉTHODES AVEC RELATIONS ===
    @Transaction
    @Query("SELECT * FROM followups WHERE id = :id AND userId = :userId")
    fun getFollowUpWithContacts(id: String, userId: String): Flow<FollowUpWithContacts?>

    @Transaction
    @Query("SELECT * FROM followups WHERE id = :id AND userId = :userId")
    fun getFollowUpFull(id: String, userId: String): Flow<FollowUpEntity?>

    @Transaction
    @Query("""
        SELECT * FROM followups 
        WHERE userId = :userId AND isDeleted = 0 AND isArchived = 0
        ORDER BY date DESC
    """)
    fun getAllActiveWithContacts(userId: String): Flow<List<FollowUpWithContacts>>

    // === GESTION DES CROSSREF ===
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFollowUpContactCrossRef(crossRef: FollowUpContactCrossRef)

    @Delete
    suspend fun deleteFollowUpContactCrossRef(crossRef: FollowUpContactCrossRef)

    @Query("DELETE FROM FollowUpContactCrossRef WHERE followUpId = :followUpId")
    suspend fun clearContactsForFollowUp(followUpId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFollowUpCallCrossRef(crossRef: FollowUpCallCrossRef)

    @Query("DELETE FROM FollowUpCallCrossRef WHERE followUpId = :followUpId")
    suspend fun clearCallsForFollowUp(followUpId: String)
}
