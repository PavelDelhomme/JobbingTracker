package com.delhomme.jobbingtrack.interviews.repositories

import com.delhomme.jobbingtrack.interviews.dao.InterviewStatusDao
import com.delhomme.jobbingtrack.interviews.entities.InterviewStatusEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InterviewStatusRepository @Inject constructor(
    private val dao: InterviewStatusDao
) {
    val all: Flow<List<InterviewStatusEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewStatusEntity?> = dao.getById(id)
    suspend fun save(entity: InterviewStatusEntity) = dao.save(entity)
    suspend fun delete(entity: InterviewStatusEntity) = dao.delete(entity.id)
    suspend fun archive(id: String) = dao.archive(id)
    suspend fun restore(id: String) = dao.restore(id)
}
