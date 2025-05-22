package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.data.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RelanceDao : DateRangeProvider<RelanceEntity> {
    override val tableName: String get() = "relances"
    override val dateColumn: String get() = "date"

    /** 1) Tout pour cet user */
    @Query("SELECT * FROM relances WHERE userId = :userId ORDER BY date DESC")
    fun getAllForUser(userId: String): Flow<List<RelanceEntity>>

    /** 2) Actifs */
    @Query("""
      SELECT * FROM relances
       WHERE userId=:userId AND isDeleted=0 AND isArchived=0
       ORDER BY date DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<RelanceEntity>>

    /** 3) Archivés */
    @Query("""
      SELECT * FROM relances
       WHERE userId=:userId AND isArchived=1 AND isDeleted=0
       ORDER BY date DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<RelanceEntity>>

    /** 4) Supprimés (corbeille) */
    @Query("""
      SELECT * FROM relances
       WHERE userId=:userId AND isDeleted=1
       ORDER BY date DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<RelanceEntity>>

    /** 5) Détail */
    @Query("SELECT * FROM relances WHERE id=:id AND userId=:userId")
    fun getByIdForUser(id: String, userId: String): Flow<RelanceEntity?>

    /** Insert ou replace */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(relance: RelanceEntity)

    /** Mise à jour (tous champs) */
    @Update
    suspend fun update(relance: RelanceEntity)

    /** Batch archive */
    @Query("UPDATE relances SET isArchived=1 WHERE id IN(:ids) AND userId=:userId")
    suspend fun archive(ids: List<String>, userId: String)

    /** Batch soft-delete */
    @Query("UPDATE relances SET isDeleted=1 WHERE id IN(:ids) AND userId=:userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    /** Batch restore */
    @Query("UPDATE relances SET isDeleted=0 WHERE id IN(:ids) AND userId=:userId")
    suspend fun restore(ids: List<String>, userId: String)

    /** Batch delete forever */
    @Query("DELETE FROM relances WHERE id IN(:ids) AND userId=:userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    /** Tout vider pour l'user */
    @Query("DELETE FROM relances WHERE userId=:userId")
    suspend fun deleteAllForUser(userId: String)

    /** Tout vider pour cet entreprise **/
    @Query("DELETE FROM relances WHERE companyId=:companyId")
    suspend fun deleteAllForCompany(companyId: String)

    /** Tout vider pour cet candidature **/
    @Query("DELETE FROM relances WHERE candidatureId=:candidatureId")
    suspend fun deleteAllForCandidature(candidatureId: String)

    /** Tout vider pour ce contact **/
    @Query("DELETE FROM relances WHERE contactId=:contactId")
    suspend fun deleteAllForContact(contactId: String)

    @RawQuery(observedEntities = [RelanceEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<RelanceEntity>>
}
