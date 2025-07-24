package com.delhomme.jobbingtrack.features.application.data.repositories

import com.delhomme.jobbingtrack.features.application.data.dao.ApplicationPlatformDao
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationPlatformEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationPlatformRepository @Inject constructor(
    private val platformDao: ApplicationPlatformDao
) {
    fun getAll(): Flow<List<ApplicationPlatformEntity>> = platformDao.getAll()

    fun getById(id: String): Flow<ApplicationPlatformEntity?> = platformDao.getById(id)

    fun getAllForUser(userId: String): Flow<List<ApplicationPlatformEntity>> = platformDao.getAllForUser(userId)

    suspend fun insert(platform: ApplicationPlatformEntity): Long = platformDao.insert(platform)

    suspend fun update(platform: ApplicationPlatformEntity) = platformDao.update(platform)

    suspend fun delete(platform: ApplicationPlatformEntity) = platformDao.delete(platform)

    suspend fun softDelete(id: String, userId: String) =
        platformDao.softDeleteById(id, userId, System.currentTimeMillis())

    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<ApplicationPlatformEntity> =
        platformDao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long) =
        platformDao.updateSyncTimestamp(ids, syncTime)
}