package com.delhomme.jobbingtrack.applications.repositories


import com.delhomme.jobbingtrack.applications.dao.ApplicationStatusDao
import com.delhomme.jobbingtrack.applications.entities.ApplicationStatusEntity
import kotlinx.coroutines.flow.Flow

class ApplicationStatusRepository(private val dao: ApplicationStatusDao) {
    val all: Flow<List<ApplicationStatusEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(status: ApplicationStatusEntity) = dao.save(status)
    suspend fun delete(status: ApplicationStatusEntity) = dao.delete(status)
}