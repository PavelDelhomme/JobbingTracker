package com.delhomme.jobbingtrack.cvs.repositories
import com.delhomme.jobbingtrack.cvs.dao.ExperienceDao
import com.delhomme.jobbingtrack.cvs.entities.ExperienceEntity
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
