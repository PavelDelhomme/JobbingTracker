package com.delhomme.jobbingtrack.features.cvs.data.sources.local
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.delhomme.jobbingtrack.features.cvs.data.entities.ProjectEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects WHERE is_deleted = 0")
    fun getAll(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM projects WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<ProjectEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ProjectEntity)

    @Delete
    suspend fun delete(entity: ProjectEntity)
}