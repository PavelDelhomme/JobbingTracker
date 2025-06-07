package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.ApplicationTypeDao
import com.delhomme.jobbingtrack.data.local.entities.ApplicationTypeEntity
import kotlinx.coroutines.flow.Flow

class ApplicationTypeRepository(private val dao: ApplicationTypeDao) {
    val all: Flow<List<ApplicationTypeEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(entity: ApplicationTypeEntity) = dao.save(entity)
    suspend fun delete(entity: ApplicationTypeEntity) = dao.delete(entity)
}
