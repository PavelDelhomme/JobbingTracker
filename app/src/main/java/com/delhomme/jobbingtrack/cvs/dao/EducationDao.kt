package com.delhomme.jobbingtrack.cvs.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.datas.entities.cvs.EducationEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface EducationDao {
    @Query("SELECT * FROM educations WHERE isDeleted = 0")
    fun getAll(): Flow<List<EducationEntity>>

    @Query("SELECT * FROM educations WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<EducationEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: EducationEntity)

    @Delete
    suspend fun delete(entity: EducationEntity)
}
