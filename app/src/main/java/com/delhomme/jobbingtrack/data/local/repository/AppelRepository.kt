package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.local.dao.AppelDao
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import kotlinx.coroutines.flow.Flow

class AppelRepository(private val dao: AppelDao) {
    fun allForUser(userId: String): Flow<List<AppelEntity>>         = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<AppelEntity>>      = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<AppelEntity>>    = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<AppelEntity>>     = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<AppelEntity?>        = dao.getByIdForUser(id, userId)

    suspend fun save(appel: AppelEntity) = dao.upsert(appel)
    suspend fun update(appel: AppelEntity) = dao.upsert(appel)
    suspend fun archive(ids: List<String>, userId: String)          = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)       = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)          = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)    = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String)                           = dao.deleteAllForUser(userId)
}