package com.delhomme.jobbingtrack.interviews.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewTypeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface InterviewTypeDao {
    @Query("SELECT * FROM interview_types WHERE isDeleted = 0")
    fun getAll(): Flow<List<InterviewTypeEntity>>

    @Query("SELECT * FROM interview_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<InterviewTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(status: InterviewTypeEntity)

    @Query("UPDATE interview_types SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: String)

    @Query("UPDATE interview_types SET isDeleted = 0 WHERE id = :id")
    suspend fun restore(id: String)

    @Query("UPDATE interview_types SET isArchived = 1 WHERE id = :id")
    suspend fun archive(id: String)
}