package com.delhomme.jobbingtrack.applications.repositories


import com.delhomme.jobbingtrack.applications.dao.ApplicationTypeDao
import com.delhomme.jobbingtrack.applications.entities.ApplicationTypeEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ApplicationTypeRepository @Inject constructor(
    private val dao: ApplicationTypeDao
) {
    val all: Flow<List<ApplicationTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<ApplicationTypeEntity?> = dao.getById(id)
    suspend fun save(entity: ApplicationTypeEntity) = dao.save(entity)
    suspend fun delete(entity: ApplicationTypeEntity) = dao.delete(entity)
}
