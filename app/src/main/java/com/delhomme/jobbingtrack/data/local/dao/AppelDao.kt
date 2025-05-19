package com.delhomme.jobbingtrack.data.local.dao


import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppelDao {
    /** Tous les appels non supprimés **/
    @Query("SELECT * FROM appels WHERE isDeleted = 0")
    fun getAll(): Flow<List<AppelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(appel: AppelEntity)

    @Query("UPDATE appels SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("UPDATE appels SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)
}