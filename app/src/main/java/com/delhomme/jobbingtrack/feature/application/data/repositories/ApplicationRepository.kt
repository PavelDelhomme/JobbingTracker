package com.delhomme.jobbingtrack.feature.application.data.repositories

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ApplicationRepository @Inject constructor(
    private val appDao: ApplicationDao,
    private val refDao: ReferenceDao
) {
    fun allForUser(userId: String): Flow<List<ApplicationEntity>> = appDao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<ApplicationEntity>> = appDao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<ApplicationEntity>> = appDao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<ApplicationEntity>> = appDao.getDeletedForUser(userId)

    fun byId(id: String, userId: String): Flow<ApplicationEntity?> = appDao.getByIdForUser(id, userId)
    fun getReferences(type: ReferenceType): refDao.getByType(type)

    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<ApplicationEntity>> = appDao.getByDateRangeForUser(userId, from, to)

    suspend fun save(candidature: ApplicationEntity) = appDao.upsert(candidature)
    suspend fun update(candidature: ApplicationEntity) = appDao.upsert(candidature)
    suspend fun archive(ids: List<String>, userId: String) = appDao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = appDao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = appDao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = appDao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = appDao.deleteAllForUser(userId)
}

