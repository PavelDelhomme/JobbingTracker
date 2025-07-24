package com.delhomme.jobbingtrack.features.contact.data.repositories

import com.delhomme.jobbingtrack.features.contact.data.dao.DepartmentTypeDao
import com.delhomme.jobbingtrack.features.contact.data.entities.DepartmentTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DepartmentTypeRepository @Inject constructor(
    private val dao: DepartmentTypeDao
) {
    val all: Flow<List<DepartmentTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<DepartmentTypeEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<DepartmentTypeEntity>> = dao.getAllForUser(userId)

    suspend fun save(departmentType: DepartmentTypeEntity) = dao.insert(departmentType)
    suspend fun update(departmentType: DepartmentTypeEntity) = dao.update(departmentType)
    suspend fun delete(id: String, userId: String) = dao.softDeleteById(id, userId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<DepartmentTypeEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}