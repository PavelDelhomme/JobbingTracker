package com.delhomme.jobbingtrack.data.local.dao.interviews

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.interview.InterviewStyleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterviewStyleDao {
    @Query("SELECT * FROM interview_styles WHERE isDeleted = 0")
    fun getAll(): Flow<List<InterviewStyleEntity>>

    @Query("SELECT * FROM interview_styles WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<InterviewStyleEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: InterviewStyleEntity)

    @Delete
    suspend fun delete(entity: InterviewStyleEntity)
}
