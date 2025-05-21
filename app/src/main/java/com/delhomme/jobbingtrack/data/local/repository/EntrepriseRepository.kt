package com.delhomme.jobbingtrack.data.local.repository


import com.delhomme.jobbingtrack.data.local.dao.EntrepriseDao
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import kotlinx.coroutines.flow.Flow

class EntrepriseRepository(private val dao: EntrepriseDao) {
    fun allForUser(userId: String): Flow<List<EntrepriseEntity>>     = dao.getAllForUser(userId)

    fun activeForUser(userId: String): Flow<List<EntrepriseEntity>>      = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<EntrepriseEntity>>    = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<EntrepriseEntity>>     = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<EntrepriseEntity?>        = dao.getByIdForUser(id, userId)

    suspend fun save(entreprise: EntrepriseEntity)                = dao.upsert(entreprise)
    suspend fun update(entreprise: EntrepriseEntity)              = dao.upsert(entreprise)
    suspend fun archive(ids: List<String>, userId: String)          = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)       = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)          = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)    = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String)                           = dao.deleteAllForUser(userId)
}