package com.delhomme.jobbingtrack.data.local.dao.applications

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.data.local.entities.application.ApplicationStatusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationStatusDao {
    @Query("SELECT * FROM application_statuses WHERE isDeleted = 0")
    fun getAll(): Flow<List<ApplicationStatusEntity>>

    @Query("SELECT * FROM application_statuses WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<ApplicationStatusEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(status: ApplicationStatusEntity)

    @Delete
    suspend fun delete(status: ApplicationStatusEntity)
}
