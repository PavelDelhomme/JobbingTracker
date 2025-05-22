package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.data.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidatureDao : DateRangeProvider<CandidatureEntity> {
    override val tableName: String get() = "candidatures"
    override val dateColumn: String get() = "applicationDate"

    @Query("SELECT * FROM candidatures WHERE userId = :userId ORDER BY applicationDate DESC")
    fun getAllForUser(userId: String): Flow<List<CandidatureEntity>>

    @Query("""
      SELECT * FROM candidatures
       WHERE userId    = :userId
         AND isDeleted = 0
         AND isArchived= 0
      ORDER BY applicationDate DESC
    """)
    fun getAllActiveForUser(userId: String): Flow<List<CandidatureEntity>>

    @Query("""
      SELECT * FROM candidatures
       WHERE userId    = :userId
         AND isArchived= 1
      ORDER BY applicationDate DESC
    """)
    fun getArchivedForUser(userId: String): Flow<List<CandidatureEntity>>

    @Query("""
      SELECT * FROM candidatures
       WHERE userId    = :userId
         AND isDeleted = 1
      ORDER BY applicationDate DESC
    """)
    fun getDeletedForUser(userId: String): Flow<List<CandidatureEntity>>

    @Query("SELECT * FROM candidatures WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<CandidatureEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cand: CandidatureEntity)

    @Update
    suspend fun update(cand: CandidatureEntity)

    @Query("UPDATE candidatures SET isArchived = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String)

    @Query("UPDATE candidatures SET isDeleted  = 1 WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    @Query("UPDATE candidatures SET isDeleted  = 0 WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE FROM candidatures WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE FROM candidatures WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)

    @RawQuery(observedEntities = [CandidatureEntity::class])
    override fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<CandidatureEntity>>

}

