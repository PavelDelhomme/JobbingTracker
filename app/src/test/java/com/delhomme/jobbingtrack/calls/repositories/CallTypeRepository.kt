package com.delhomme.jobbingtrack.calls.repositories

import com.delhomme.jobbingtrack.calls.dao.CallTypeDao
import com.delhomme.jobbingtrack.calls.entities.CallTypeEntity
import kotlinx.coroutines.flow.Flow

class CallTypeRepository(private val dao: CallTypeDao) {
    val all: Flow<List<CallTypeEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(entity: CallTypeEntity) = dao.save(entity)
    suspend fun delete(entity: CallTypeEntity) = dao.delete(entity)
}
