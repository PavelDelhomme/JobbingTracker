package com.delhomme.jobbingtrack.data.local.dao


import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface AppelDao {
    @Query("SELECT * FROM appels WHERE userId = :userId ORDER BY dateTime DESC")
    fun getAllForUser(userId: String): Flow<List<AppelEntity>>

    @Query("""
    SELECT * FROM appels
     WHERE userId    = :userId
       AND isDeleted = 0
       AND isArchived= 0
    ORDER BY dateTime DESC
  """)
    fun getAllActiveForUser(userId: String): Flow<List<AppelEntity>>

    @Query("""
    SELECT * FROM appels
     WHERE userId    = :userId
       AND isArchived= 1
    ORDER BY dateTime DESC
  """)
    fun getArchivedForUser(userId: String): Flow<List<AppelEntity>>

    @Query("""
    SELECT * FROM appels
     WHERE userId    = :userId
       AND isDeleted = 1
    ORDER BY dateTime DESC
  """)
    fun getDeletedForUser(userId: String): Flow<List<AppelEntity>>

    @Query("SELECT * FROM appels WHERE id = :id AND userId = :userId")
    fun getByIdForUser(id: String, userId: String): Flow<AppelEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(appel: AppelEntity)

    @Query("UPDATE appels SET isArchived = 1    WHERE id IN(:ids) AND userId = :userId")
    suspend fun archive(ids: List<String>, userId: String)

    @Query("UPDATE appels SET isDeleted  = 1    WHERE id IN(:ids) AND userId = :userId")
    suspend fun softDelete(ids: List<String>, userId: String)

    @Query("UPDATE appels SET isDeleted  = 0    WHERE id IN(:ids) AND userId = :userId")
    suspend fun restore(ids: List<String>, userId: String)

    @Query("DELETE   FROM appels            WHERE id IN(:ids) AND userId = :userId")
    suspend fun deleteForever(ids: List<String>, userId: String)

    @Query("DELETE   FROM appels            WHERE userId = :userId")
    suspend fun deleteAllForUser(userId: String)
}
