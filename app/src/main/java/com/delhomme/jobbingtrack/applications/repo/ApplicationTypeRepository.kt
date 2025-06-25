package com.delhomme.jobbingtrack.applications.repo

import com.delhomme.jobbingtrack.applications.ApplicationTypeEntity
import com.delhomme.jobbingtrack.applications.dao.ApplicationTypeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ApplicationTypeRepository @Inject constructor(
    private val dao: ApplicationTypeDao
) {
    val all: Flow<List<ApplicationTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<ApplicationTypeEntity?> = dao.getById(id)
    suspend fun save(entity: ApplicationTypeEntity) = dao.save(entity)
    suspend fun delete(entity: ApplicationTypeEntity) = dao.delete(entity)
}