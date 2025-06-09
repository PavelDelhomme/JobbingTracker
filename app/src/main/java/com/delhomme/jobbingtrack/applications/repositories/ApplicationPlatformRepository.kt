package com.delhomme.jobbingtrack.applications.repositories

import com.delhomme.jobbingtrack.applications.dao.ApplicationPlatformDao
import com.delhomme.jobbingtrack.applications.entities.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.applications.entities.ApplicationStatusEntity
import kotlinx.coroutines.flow.Flow


class ApplicationPlatformRepository(dao: ApplicationPlatformDao) {
    private val dao: ApplicationPlatformDao
    init {
        this.dao = dao
    }
    val all: Flow<List<ApplicationPlatformEntity>> = dao.getAll()

    fun byId(id: String) = dao.getById(id)

    suspend fun save(status: ApplicationPlatformEntity) = dao.save(status)

    suspend fun delete(status: ApplicationPlatformEntity) = dao.delete(status)
}
