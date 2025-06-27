package com.delhomme.jobbingtrack.features.cvs.data.repositories

import com.delhomme.jobbingtrack.features.cvs.data.entities.ProjectEntity
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.ProjectDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProjectRepository @Inject constructor(
    private val dao: ProjectDao
) {
    val all: Flow<List<ProjectEntity>> = dao.getAll()
    fun byId(id: String): Flow<ProjectEntity?> = dao.getById(id)
    suspend fun save(entity: ProjectEntity) = dao.save(entity)
    suspend fun delete(entity: ProjectEntity) = dao.delete(entity)
}