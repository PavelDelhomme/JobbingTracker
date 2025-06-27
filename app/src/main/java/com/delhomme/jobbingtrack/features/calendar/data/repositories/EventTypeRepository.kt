package com.delhomme.jobbingtrack.features.calendar.data.repositories

import com.delhomme.jobbingtrack.features.calendar.data.dao.EventTypeDao
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventTypeEntity
import kotlinx.coroutines.flow.Flow


class EventTypeRepository(private val dao: EventTypeDao) {
    val all: Flow<List<EventTypeEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(entity: EventTypeEntity) = dao.save(entity)
    suspend fun delete(entity: EventTypeEntity) = dao.delete(entity)
}