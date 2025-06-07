package com.delhomme.jobbingtrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.data.local.entities.InterviewStatusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterviewStatusDao {
    @Query("SELECT * FROM interview_status WHERE isDeleted = 0")
    fun getAll(): Flow<List<InterviewStatusEntity>>

    @Query("SELECT * FROM interview_status WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<InterviewStatusEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(status: InterviewStatusEntity)

    @Query("UPDATE interview_status SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)

    @Query("UPDATE interview_status SET isDeleted = 0 WHERE id = :id")
    suspend fun restore(id: String)

    @Query("UPDATE interview_status SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)
}