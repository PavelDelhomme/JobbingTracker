package com.delhomme.jobbingtrack.cvs.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.datas.entities.cvs.SkillEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SkillDao {
    @Query("SELECT * FROM skills WHERE isDeleted = 0")
    fun getAll(): Flow<List<SkillEntity>>

    @Query("SELECT * FROM skills WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<SkillEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: SkillEntity)

    @Delete
    suspend fun delete(entity: SkillEntity)
}
