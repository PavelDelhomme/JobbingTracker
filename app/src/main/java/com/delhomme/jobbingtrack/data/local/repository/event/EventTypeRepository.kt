package com.delhomme.jobbingtrack.data.local.repository.event

import com.delhomme.jobbingtrack.data.local.dao.event.EventTypeDao
import com.delhomme.jobbingtrack.data.local.entities.event.EventTypeEntity
import kotlinx.coroutines.flow.Flow

class EventTypeRepository(private val dao: EventTypeDao) {
    val all: Flow<List<EventTypeEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(entity: EventTypeEntity) = dao.save(entity)
    suspend fun delete(entity: EventTypeEntity) = dao.delete(entity)
}
