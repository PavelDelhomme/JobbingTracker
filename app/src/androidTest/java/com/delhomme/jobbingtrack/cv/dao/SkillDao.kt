package com.delhomme.jobbingtrack.cv.dao


import androidx.room.*
import com.delhomme.jobbingtrack.cv.entities.SkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillDao {
    @Query("SELECT * FROM skills WHERE isDeleted = 0")
    fun getAll(): Flow<List<SkillEntity>>

    @Query("SELECT * FROM skills WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<SkillEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: SkillEntity)

    @Delete
    suspend fun delete(entity: SkillEntity)
}
