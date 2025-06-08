package com.delhomme.jobbingtrack.cv.dao


import androidx.room.*
import com.delhomme.jobbingtrack.cv.entities.EducationEntity
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
