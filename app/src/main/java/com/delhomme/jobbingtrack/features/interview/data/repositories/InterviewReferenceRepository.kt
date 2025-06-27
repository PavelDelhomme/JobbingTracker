package com.delhomme.jobbingtrack.features.interview.data.repositories

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




class InterviewStyleRepository @Inject constructor(
    private val dao: InterviewStyleDao
) {
    val all: Flow<List<InterviewStyleEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewStyleEntity?> = dao.getById(id)
    suspend fun save(entity: InterviewStyleEntity) = dao.save(entity)
    suspend fun delete(entity: InterviewStyleEntity) = dao.delete(entity)
}



class InterviewTypeRepository @Inject constructor(
    private val dao: InterviewTypeDao
) {
    val all: Flow<List<InterviewTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewTypeEntity?> = dao.getById(id)
    suspend fun save(entity: InterviewTypeEntity) = dao.save(entity)
    suspend fun delete(entity: InterviewTypeEntity) = dao.delete(entity.id)
}