package com.delhomme.jobbingtrack.applications.dao


import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.delhomme.jobbingtrack.data.interfaces.DateRangeProvider
import com.delhomme.jobbingtrack.data.local.entities.application.ApplicationEntity
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

}
