package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RelanceDao {

    /** 1) Tout pour cet user (actifs + archivés + supprimés) */
    @Query("""
    SELECT * FROM relances
     WHERE userId = :userId
    ORDER BY date DESC
  """)
    fun getAllForUser(userId: String): Flow<List<RelanceEntity>>

    /** 2) Actifs (ni supprimés ni archivés) */
    @Query("""
    SELECT * FROM relances
     WHERE userId    = :userId
       AND isDeleted = 0
       AND isArchived= 0
    ORDER BY date DESC
  """)
    fun getAllActiveForUser(userId: String): Flow<List<RelanceEntity>>

    /** 3) Archivés */
    @Query("""
    SELECT * FROM relances
     WHERE userId    = :userId
       AND isArchived = 1
       AND isDeleted = 0
    ORDER BY date DESC
  """)
    fun getArchivedForUser(userId: String): Flow<List<RelanceEntity>>

    /** 4) Supprimés (corbeille) */
    @Query("""
    SELECT * FROM relances
     WHERE userId    = :userId
       AND isDeleted = 1
    ORDER BY date DESC
  """)
    fun getDeletedForUser(userId: String): Flow<List<RelanceEntity>>

    /** 5) Détail par ID + user */
    @Query("""
    SELECT * FROM relances
     WHERE id     = :id
       AND userId = :userId
  """)
    fun getByIdForUser(id: String, userId: String): Flow<RelanceEntity?>

    /** Insert ou remplace */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(relance: RelanceEntity)

    /** Mise à jour (tous champs) */
    @Update
    suspend fun update(relance: RelanceEntity)

    /** Batch archive */
    @Query("""
    UPDATE relances
     SET isArchived = 1
     WHERE id     IN (:ids)
       AND userId = :userId
  """)
    suspend fun archive(ids: List<String>, userId: String)

    /** Batch suppression douce */
    @Query("""
    UPDATE relances
     SET isDeleted = 1
     WHERE id     IN (:ids)
       AND userId = :userId
  """)
    suspend fun softDelete(ids: List<String>, userId: String)

    /** Batch restore */
    @Query("""
    UPDATE relances
     SET isDeleted = 0
     WHERE id     IN (:ids)
       AND userId = :userId
  """)
    suspend fun restore(ids: List<String>, userId: String)

    /** Batch suppression définitive */
    @Query("""
    DELETE FROM relances
     WHERE id     IN (:ids)
       AND userId = :userId
  """)
    suspend fun deleteForever(ids: List<String>, userId: String)

    /** Tout supprimer pour cet user */
    @Query("""
    DELETE FROM relances
     WHERE userId = :userId
  """)
    suspend fun deleteAllForUser(userId: String)
}
