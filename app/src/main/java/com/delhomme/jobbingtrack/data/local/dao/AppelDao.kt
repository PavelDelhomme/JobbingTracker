package com.delhomme.jobbingtrack.data.local.dao


import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppelDao {
    // 1) Tous les appels (même archivés ou supprimés)
    @Query("SELECT * FROM appels ORDER BY dateTime DESC")
    fun getAll(): Flow<List<AppelEntity>>

    // 2) Appels actifs (ni supprimés, ni archivés)
    @Query("""
      SELECT * FROM appels
       WHERE isDeleted = 0
         AND isArchived = 0
       ORDER BY dateTime DESC
    """)
    fun getAllActive(): Flow<List<AppelEntity>>

    // 3) Appels archivés
    @Query("SELECT * FROM appels WHERE isArchived = 1 ORDER BY dateTime DESC")
    fun getAllArchived(): Flow<List<AppelEntity>>

    // 4) Appels supprimés (corbeille)
    @Query("SELECT * FROM appels WHERE isDeleted = 1 ORDER BY dateTime DESC")
    fun getAllDeleted(): Flow<List<AppelEntity>>

    // 5) Détail d’un appel par son id
    @Query("SELECT * FROM appels WHERE id = :id")
    fun getById(id: String): Flow<AppelEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(appel: AppelEntity)

    @Query("UPDATE appels SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)

    @Query("UPDATE appels SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)
}