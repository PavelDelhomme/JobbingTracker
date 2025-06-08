package com.delhomme.jobbingtrack.data.local.dao.event

import androidx.room.*
import com.delhomme.jobbingtrack.data.local.entities.event.EventTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventTypeDao {
    @Query("SELECT * FROM event_types WHERE isDeleted = 0")
    fun getAll(): Flow<List<EventTypeEntity>>

    @Query("SELECT * FROM event_types WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<EventTypeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: EventTypeEntity)

    @Delete
    suspend fun delete(entity: EventTypeEntity)
}
