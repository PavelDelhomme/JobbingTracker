package com.delhomme.jobbingtrack.data.local.repository


import com.delhomme.jobbingtrack.data.local.dao.CandidatureDao
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import kotlinx.coroutines.flow.Flow

class CandidatureRepository(private val dao: CandidatureDao) {
    /** Flux de toutes les candidatures non supprimées */
    val candidatures: Flow<List<CandidatureEntity>> = dao.getAll()

    suspend fun save(cand: CandidatureEntity) = dao.insert(cand)
    suspend fun archive(id: String)            = dao.archive(id)
    suspend fun delete(id: String)             = dao.delete(id)
}