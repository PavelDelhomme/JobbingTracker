package com.delhomme.jobbingtrack.features.cvs.data.sources.local
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.delhomme.jobbingtrack.features.cvs.data.entities.EducationEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface EducationDao {
    @Query("SELECT * FROM educations WHERE is_deleted = 0")
    fun getAll(): Flow<List<EducationEntity>>

    @Query("SELECT * FROM educations WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<EducationEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: EducationEntity)

    @Delete
    suspend fun delete(entity: EducationEntity)
}