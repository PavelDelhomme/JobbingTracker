package com.delhomme.jobbingtrack.features.application.data.repositories

import com.delhomme.jobbingtrack.features.application.data.dao.ApplicationTypeDao
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationTypeRepository @Inject constructor(
    private val typeDao: ApplicationTypeDao
) {
    fun getAll(): Flow<List<ApplicationTypeEntity>> = typeDao.getAll()

    fun getById(id: String): Flow<ApplicationTypeEntity?> = typeDao.getById(id)

    fun getAllForUser(userId: String): Flow<List<ApplicationTypeEntity>> = typeDao.getAllForUser(userId)

    suspend fun insert(type: ApplicationTypeEntity): Long = typeDao.insert(type)

    suspend fun update(type: ApplicationTypeEntity) = typeDao.update(type)

    suspend fun delete(type: ApplicationTypeEntity) = typeDao.delete(type)

    suspend fun softDelete(id: String, userId: String) =
        typeDao.softDeleteById(id, userId, System.currentTimeMillis())

    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<ApplicationTypeEntity> =
        typeDao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long) =
        typeDao.updateSyncTimestamp(ids, syncTime)
}