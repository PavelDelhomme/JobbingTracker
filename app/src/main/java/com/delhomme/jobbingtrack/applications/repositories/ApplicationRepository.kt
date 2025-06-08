package com.delhomme.jobbingtrack.applications.repositories


import com.delhomme.jobbingtrack.data.local.dao.applications.ApplicationDao
import com.delhomme.jobbingtrack.data.local.entities.application.ApplicationEntity
import kotlinx.coroutines.flow.Flow

class ApplicationRepository(private val dao: ApplicationDao) {
    fun allForUser(userId: String): Flow<List<ApplicationEntity>>       = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<ApplicationEntity>>      = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<ApplicationEntity>>    = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<ApplicationEntity>>     = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<ApplicationEntity?>        = dao.getByIdForUser(id, userId)

    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<ApplicationEntity>>      = dao.getByDateRangeForUser(userId, from, to)

    suspend fun save(candidature: ApplicationEntity)                = dao.upsert(candidature)
    suspend fun update(candidature: ApplicationEntity)              = dao.upsert(candidature)
    suspend fun archive(ids: List<String>, userId: String)          = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)       = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)          = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)    = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String)                           = dao.deleteAllForUser(userId)
}