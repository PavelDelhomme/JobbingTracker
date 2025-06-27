package com.delhomme.jobbingtrack.features.application.data.repositories

import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationStatusEntity
import com.delhomme.jobbingtrack.features.application.data.sources.local.ApplicationStatusDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ApplicationStatusRepository @Inject constructor(
    private val dao: ApplicationStatusDao
) {
    val all: Flow<List<ApplicationStatusEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(status: ApplicationStatusEntity) = dao.save(status)
    suspend fun delete(status: ApplicationStatusEntity) = dao.delete(status)
    fun allForUser(userId: String) = dao.getAllForUser(userId)
}
