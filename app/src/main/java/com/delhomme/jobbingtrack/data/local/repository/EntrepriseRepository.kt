package com.delhomme.jobbingtrack.data.local.repository


import com.delhomme.jobbingtrack.data.local.dao.EntrepriseDao
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import kotlinx.coroutines.flow.Flow

class EntrepriseRepository(private val dao: EntrepriseDao) {
    val entreprises: Flow<List<EntrepriseEntity>> = dao.getAll()

    suspend fun save(entreprise: EntrepriseEntity) = dao.insert(entreprise)
    suspend fun archive(id: String)              = dao.archive(id)
    suspend fun delete(id: String)               = dao.deleteById(id)
}