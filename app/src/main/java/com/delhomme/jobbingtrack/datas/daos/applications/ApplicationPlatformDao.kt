package com.delhomme.jobbingtrack.datas.daos.applications

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationPlatformEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ApplicationPlatformDao {
    @Query("SELECT * FROM application_platforms WHERE isDeleted = 0")
    fun getAll(): Flow<List<ApplicationPlatformEntity>>

    @Query("SELECT * FROM application_platforms WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<ApplicationPlatformEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(status: ApplicationPlatformEntity)

    @Query("SELECT * FROM application_platforms WHERE userId = :userId")
    fun getAllForUser(userId: String): Flow<List<ApplicationPlatformEntity>>
    @Delete
    suspend fun delete(status: ApplicationPlatformEntity)
}
