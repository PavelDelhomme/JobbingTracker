package com.delhomme.jobbingtrack.features.cvs.data.repositories

import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.cvs.data.sources.local.CVDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CvRepository @Inject constructor(
    private val dao: CVDao
) {
    val all: Flow<List<CVEntity>> = dao.getAll()
    fun byId(id: String): Flow<CVEntity?> = dao.getById(id)
    suspend fun save(entity: CVEntity) = dao.save(entity)
    suspend fun delete(entity: CVEntity) = dao.delete(entity)
}