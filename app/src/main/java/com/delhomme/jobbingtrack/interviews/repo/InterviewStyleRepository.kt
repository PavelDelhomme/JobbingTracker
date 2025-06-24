package com.delhomme.jobbingtrack.interviews.repo

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class InterviewStyleRepository @Inject constructor(
    private val dao: InterviewStyleDao
) {
    val all: Flow<List<InterviewStyleEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewStyleEntity?> = dao.getById(id)
    suspend fun save(entity: InterviewStyleEntity) = dao.save(entity)
    suspend fun delete(entity: InterviewStyleEntity) = dao.delete(entity)
}

