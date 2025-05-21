package com.delhomme.jobbingtrack.data.local.repository


import com.delhomme.jobbingtrack.data.local.dao.CandidatureDao
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import kotlinx.coroutines.flow.Flow

class CandidatureRepository(private val dao: CandidatureDao) {
    fun allForUser(userId: String): Flow<List<CandidatureEntity>>       = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<CandidatureEntity>>      = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<CandidatureEntity>>    = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<CandidatureEntity>>     = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<CandidatureEntity?>        = dao.getByIdForUser(id, userId)

    suspend fun save(candidature: CandidatureEntity)                = dao.upsert(candidature)
    suspend fun update(candidature: CandidatureEntity)              = dao.upsert(candidature)
    suspend fun archive(ids: List<String>, userId: String)          = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)       = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)          = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)    = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String)                           = dao.deleteAllForUser(userId)
}