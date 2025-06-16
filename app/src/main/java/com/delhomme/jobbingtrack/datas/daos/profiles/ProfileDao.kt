package com.delhomme.jobbingtrack.datas.daos.profiles

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.delhomme.jobbingtrack.datas.entities.profiles.ProfilEntity
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
