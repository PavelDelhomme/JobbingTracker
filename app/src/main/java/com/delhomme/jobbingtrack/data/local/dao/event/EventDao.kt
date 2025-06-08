package com.delhomme.jobbingtrack.data.local.dao.event


import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.data.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.data.local.entities.event.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao : DateRangeProvider<EventEntity> {
    override val tableName: String get() = "events"
    override val dateColumn: String get() = "startDate"

    /** 1) Tous les événements pour un user (actifs + archivés + supprimés) */
    @Query("""
    SELECT * FROM events
     WHERE userId = :userId
    ORDER BY startDate DESC
  """)
    fun getAllForUser(userId: String): Flow<List<EventEntity>>

    /** 2) Actifs (ni supprimés ni archivés) */
    @Query("""
    SELECT * FROM events
     WHERE userId = :userId
       AND isDeleted = 0
       AND isArchived = 0
    ORDER BY startDate DESC
  """)
    fun getAllActiveForUser(userId: String): Flow<List<EventEntity>>


    /** 3) Archivés */
    @Query("""
    SELECT * FROM events
     WHERE userId = :userId
       AND isArchived = 1
       AND isDeleted = 0
    ORDER BY startDate DESC
  """)
    fun getArchivedForUser(userId: String): Flow<List<EventEntity>>

    /** 4) Supprimés (corbeille) */
    @Query("""
    SELECT * FROM events
     WHERE userId = :userId
       AND isDeleted = 1
    ORDER BY startDate DESC
  """)
    fun getDeletedForUser(userId: String): Flow<List<EventEntity>>

    /** 5) Détail par ID + userId */
    @Query("""
    SELECT * FROM events
     WHERE id = :id
       AND userId = :userId
  """)
    fun getByIdForUser(id: String, userId: String): Flow<EventEntity?>

    /** Upsert (insert ou remplace) */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(event: EventEntity)

    /** Mise à jour partielle (ou full) */
    @Update
    suspend fun update(event: EventEntity)

    /** Batch : archive “soft” */
    @Query("""
    UPDATE events
     SET isArchived = 1
     WHERE id   IN (:ids)
       AND userId = :userId
  """)
    suspend fun archive(ids: List<String>, userId: String)

    /** Batch : suppression douce */
    @Query("""
    UPDATE events
     SET isDeleted = 1
     WHERE id   IN (:ids)
       AND userId = :userId
  """)
    suspend fun softDelete(ids: List<String>, userId: String)

    /** Batch : restore */
    @Query("""
    UPDATE events
     SET isDeleted = 0
     WHERE id   IN (:ids)
       AND userId = :userId
  """)
    suspend fun restore(ids: List<String>, userId: String)

    /** Batch : delete forever */
    @Query("""
    DELETE FROM events
     WHERE id   IN (:ids)
       AND userId = :userId
  """)
    suspend fun deleteForever(ids: List<String>, userId: String)

    /** Tout supprimer pour cet user */
    @Query("""
    DELETE FROM events
     WHERE userId = :userId
  """)
    suspend fun deleteAllForUser(userId: String)

    @RawQuery(observedEntities = [EventEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<EventEntity>>
}