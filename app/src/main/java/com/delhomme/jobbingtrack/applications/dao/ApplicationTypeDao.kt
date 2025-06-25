package com.delhomme.jobbingtrack.applications.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.applications.ApplicationTypeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ApplicationTypeDao {
    @Query("SELECT * FROM application_types WHERE isDeleted = 0")
    fun getAll(): Flow<List<ApplicationTypeEntity>>

    @Query("SELECT * FROM application_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<ApplicationTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ApplicationTypeEntity)

    @Delete
    suspend fun delete(entity: ApplicationTypeEntity)
}