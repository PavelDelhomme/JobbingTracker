package com.delhomme.jobbingtrack.features.cvs.data.repositories

import com.delhomme.jobbingtrack.features.cvs.data.entities.ExperienceEntity
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.ExperienceDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ExperienceRepository @Inject constructor(
    private val dao: ExperienceDao
) {
    val all: Flow<List<ExperienceEntity>> = dao.getAll()
    fun byId(id: String): Flow<ExperienceEntity?> = dao.getById(id)
    suspend fun save(entity: ExperienceEntity) = dao.save(entity)
    suspend fun update(entity: ExperienceEntity) = dao.update(entity)
    suspend fun delete(entity: ExperienceEntity) = dao.delete(entity)
}