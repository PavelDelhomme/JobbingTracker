package com.delhomme.jobbingtrack.features.application.data.sources.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationWithCalls
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationWithContacts
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationWithFollowUps
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationWithInterviews
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationWithRelations
import kotlinx.coroutines.flow.Flow


@Dao
interface ApplicationDao : BaseDao<ApplicationEntity> {
    //override val tableName: String get() = "applications"
    //override val dateColumn: String get() = "applicationDate"

    // Récupération avec relations
    @Transaction
    @Query("SELECT * FROM applications WHERE id = :id")
    suspend fun getWithRelations(id: String): ApplicationWithRelations

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
    override suspend fun update(cand: ApplicationEntity)

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
    fun getByDateRange(query: SimpleSQLiteQuery): Flow<List<ApplicationEntity>>
}