package com.delhomme.jobbingtrack.applications.repositories


import com.delhomme.jobbingtrack.applications.dao.ApplicationTypeDao
import com.delhomme.jobbingtrack.applications.entities.ApplicationTypeEntity
import kotlinx.coroutines.flow.Flow

class ApplicationTypeRepository(private val dao: ApplicationTypeDao) {
    val all: Flow<List<ApplicationTypeEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(entity: ApplicationTypeEntity) = dao.save(entity)
    suspend fun delete(entity: ApplicationTypeEntity) = dao.delete(entity)
}
