package com.delhomme.jobbingtrack.features.cvs.data.repositories

import com.delhomme.jobbingtrack.features.cvs.data.entities.CollaboratorEntity
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.CollaboratorDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollaboratorRepository @Inject constructor(
    private val dao: CollaboratorDao
) {
    val all: Flow<List<CollaboratorEntity>> = dao.getAll()
    fun byId(id: String): Flow<CollaboratorEntity?> = dao.getById(id)
    suspend fun save(entity: CollaboratorEntity) = dao.save(entity)
    suspend fun update(entity: CollaboratorEntity) = dao.update(entity)
    suspend fun delete(entity: CollaboratorEntity) = dao.delete(entity)
}