package com.delhomme.jobbingtrack.applications.repositories

import com.delhomme.jobbingtrack.applications.dao.ApplicationPlatformDao
import com.delhomme.jobbingtrack.applications.entities.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.applications.entities.ApplicationStatusEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationPlatformRepository @Inject constructor(
    private val dao: ApplicationPlatformDao
) {
    val all: Flow<List<ApplicationPlatformEntity>> = dao.getAll()

    fun byId(id: String) = dao.getById(id)

    suspend fun save(status: ApplicationPlatformEntity) = dao.save(status)

    suspend fun delete(status: ApplicationPlatformEntity) = dao.delete(status)
}
