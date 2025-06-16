package com.delhomme.jobbingtrack.datas.daos.cvs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.delhomme.jobbingtrack.datas.entities.cvs.ExperienceEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ExperienceDao {
    @Query("SELECT * FROM experiences WHERE isDeleted = 0")
    fun getAll(): Flow<List<ExperienceEntity>>

    @Query("SELECT * FROM experiences WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<ExperienceEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ExperienceEntity)

    @Update
    suspend fun update(entity: ExperienceEntity)

    @Delete
    suspend fun delete(entity: ExperienceEntity)
}
