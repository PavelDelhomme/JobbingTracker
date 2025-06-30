package com.delhomme.jobbingtrack.features.cvs.data.sources.local
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.delhomme.jobbingtrack.core.database.BaseDao
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.CvWithRelations
import kotlinx.coroutines.flow.Flow


@Dao
interface CVDao : BaseDao<CVEntity> {
    @Query("SELECT * FROM cvs WHERE isDeleted = 0")
    fun getAll(): Flow<List<CVEntity>>

    @Query("SELECT * FROM cvs WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<CVEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: CVEntity)

    @Transaction
    @Query("SELECT * FROM cvs WHERE id = :id")
    suspend fun getWithRelations(id: String): CvWithRelations

    @Delete
    override suspend fun delete(entity: CVEntity)
}