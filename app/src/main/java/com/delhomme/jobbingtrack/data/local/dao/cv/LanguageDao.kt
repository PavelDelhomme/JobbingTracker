package com.delhomme.jobbingtrack.data.local.dao.cv

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.cv.LanguageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageDao {
    @Query("SELECT * FROM languages WHERE isDeleted = 0")
    fun getAll(): Flow<List<LanguageEntity>>

    @Query("SELECT * FROM languages WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<LanguageEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: LanguageEntity)

    @Delete
    suspend fun delete(entity: LanguageEntity)
}
