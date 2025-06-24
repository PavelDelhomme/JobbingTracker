package com.delhomme.jobbingtrack.cvs.repo

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CvRepository @Inject constructor(
    private val dao: CVDao
) {
    val all: Flow<List<CVEntity>> = dao.getAll()
    fun byId(id: String): Flow<CVEntity?> = dao.getById(id)
    suspend fun save(entity: CVEntity) = dao.save(entity)
    suspend fun delete(entity: CVEntity) = dao.delete(entity)

    fun getByUserId(userId: String) = dao.getByUserId(userId)
}

