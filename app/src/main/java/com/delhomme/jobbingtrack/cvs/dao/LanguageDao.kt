package com.delhomme.jobbingtrack.cvs.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.datas.entities.cvs.LanguageEntity
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
