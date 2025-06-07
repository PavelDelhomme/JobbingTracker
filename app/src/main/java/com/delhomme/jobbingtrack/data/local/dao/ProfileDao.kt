package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.ProfilEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profiles ORDER BY id")
    fun getAll(): Flow<List<ProfilEntity>>

    @Query("SELECT * FROM profiles WHERE id = :id")
    fun getById(id: String): Flow<ProfilEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(profile: ProfilEntity)

    @Query("SELECT * FROM profiles WHERE id = :id LIMIT 1")
    suspend fun getByIdNow(id: String): ProfilEntity?

    @Update
    suspend fun update(profile: ProfilEntity)

    @Query("DELETE FROM profiles WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM profiles")
    suspend fun deleteAll()
}
