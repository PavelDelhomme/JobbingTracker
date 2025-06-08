package com.delhomme.jobbingtrack.events.repositories


import com.delhomme.jobbingtrack.events.dao.EventTypeDao
import com.delhomme.jobbingtrack.events.entities.EventTypeEntity
import kotlinx.coroutines.flow.Flow

class EventTypeRepository(private val dao: EventTypeDao) {
    val all: Flow<List<EventTypeEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(entity: EventTypeEntity) = dao.save(entity)
    suspend fun delete(entity: EventTypeEntity) = dao.delete(entity)
}
