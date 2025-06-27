package com.delhomme.jobbingtrack.features.application.data.repositories

import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.features.application.data.sources.local.ApplicationPlatformDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ApplicationPlatformRepository @Inject constructor(
    private val dao: ApplicationPlatformDao
) {
    val all: Flow<List<ApplicationPlatformEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    fun allForUser(userId: String) = dao.getAllForUser(userId)
    suspend fun save(status: ApplicationPlatformEntity) = dao.save(status)
    suspend fun delete(status: ApplicationPlatformEntity) = dao.delete(status)
}


