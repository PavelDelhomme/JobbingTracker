package com.delhomme.jobbingtrack.features.contact.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.features.contact.data.entities.PositionTypeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PositionTypeDao {
    @Query("SELECT * FROM position_types")
    fun getAll(): Flow<List<PositionTypeEntity>>

    @Query("SELECT * FROM position_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<PositionTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: PositionTypeEntity)

    @Delete
    suspend fun delete(entity: PositionTypeEntity)
}
