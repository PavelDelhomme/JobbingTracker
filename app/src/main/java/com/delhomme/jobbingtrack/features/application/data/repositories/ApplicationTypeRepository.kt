package com.delhomme.jobbingtrack.features.application.data.repositories

import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationTypeEntity
import com.delhomme.jobbingtrack.features.application.data.sources.local.ApplicationTypeDao
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
