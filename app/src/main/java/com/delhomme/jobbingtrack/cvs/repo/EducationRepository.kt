package com.delhomme.jobbingtrack.cvs.repo

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class EducationRepository @Inject constructor(
    private val dao: EducationDao
) {
    val all: Flow<List<EducationEntity>> = dao.getAll()
    fun byId(id: String): Flow<EducationEntity?> = dao.getById(id)
    suspend fun save(entity: EducationEntity) = dao.save(entity)
    suspend fun delete(entity: EducationEntity) = dao.delete(entity)
}