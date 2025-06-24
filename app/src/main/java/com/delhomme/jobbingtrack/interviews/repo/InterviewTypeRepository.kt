package com.delhomme.jobbingtrack.interviews.repo

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class InterviewTypeRepository @Inject constructor(
    private val dao: InterviewTypeDao
) {
    val all: Flow<List<InterviewTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewTypeEntity?> = dao.getById(id)
    suspend fun save(entity: InterviewTypeEntity) = dao.save(entity)
    suspend fun delete(entity: InterviewTypeEntity) = dao.delete(entity.id)
}