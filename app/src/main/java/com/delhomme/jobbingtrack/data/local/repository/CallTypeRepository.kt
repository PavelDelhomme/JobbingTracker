package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.CallTypeDao
import com.delhomme.jobbingtrack.data.local.entities.CallTypeEntity
import kotlinx.coroutines.flow.Flow

class CallTypeRepository(private val dao: CallTypeDao) {
    val all: Flow<List<CallTypeEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(entity: CallTypeEntity) = dao.save(entity)
    suspend fun delete(entity: CallTypeEntity) = dao.delete(entity)
}
