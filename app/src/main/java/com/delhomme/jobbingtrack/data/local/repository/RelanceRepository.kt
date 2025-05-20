package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.RelanceDao
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import kotlinx.coroutines.flow.Flow

class RelanceRepository(private val dao: RelanceDao) {
    val relances: Flow<List<RelanceEntity>> = dao.getAll()

    suspend fun save(relance: RelanceEntity) = dao.insert(relance)
    suspend fun archive(id: String) = dao.archive(id)
    suspend fun delete(id: String) = dao.delete(id)
}