package com.delhomme.jobbingtrack.cv.dao


import androidx.room.*
import com.delhomme.jobbingtrack.cv.entities.CVEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CVDao {
    @Query("SELECT * FROM cvs WHERE isDeleted = 0")
    fun getAll(): Flow<List<CVEntity>>

    @Query("SELECT * FROM cvs WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<CVEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: CVEntity)

    @Delete
    suspend fun delete(entity: CVEntity)
}
