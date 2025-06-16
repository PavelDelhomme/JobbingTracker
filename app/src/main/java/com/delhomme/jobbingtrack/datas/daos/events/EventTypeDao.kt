package com.delhomme.jobbingtrack.datas.daos.events

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delhomme.jobbingtrack.datas.entities.events.EventTypeEntity
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
