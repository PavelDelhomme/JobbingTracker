package com.delhomme.jobbingtrack.features.application.data.repositories

import com.delhomme.jobbingtrack.features.application.data.dao.ApplicationStatusDao
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationStatusEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationStatusRepository @Inject constructor(
    private val statusDao: ApplicationStatusDao
) {
    fun getAll(): Flow<List<ApplicationStatusEntity>> = statusDao.getAll()

    fun getById(id: String): Flow<ApplicationStatusEntity?> = statusDao.getById(id)

    fun getAllForUser(userId: String): Flow<List<ApplicationStatusEntity>> = statusDao.getAllForUser(userId)

    suspend fun insert(status: ApplicationStatusEntity): Long = statusDao.insert(status)

    suspend fun update(status: ApplicationStatusEntity) = statusDao.update(status)

    suspend fun delete(status: ApplicationStatusEntity) = statusDao.delete(status)

    suspend fun softDelete(id: String, userId: String) =
        statusDao.softDeleteById(id, userId, System.currentTimeMillis())

    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<ApplicationStatusEntity> =
        statusDao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long) =
        statusDao.updateSyncTimestamp(ids, syncTime)
}