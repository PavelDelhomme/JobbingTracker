package com.delhomme.jobbingtrack.features.contact.data.repositories

import com.delhomme.jobbingtrack.features.contact.data.dao.DepartmentTypeDao
import com.delhomme.jobbingtrack.features.contact.data.entities.DepartmentTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DepartmentTypeRepository @Inject constructor(
    private val dao: DepartmentTypeDao
) {
    val all: Flow<List<DepartmentTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<DepartmentTypeEntity?> = dao.getById(id)
    suspend fun save(entity: DepartmentTypeEntity) = dao.save(entity)
    suspend fun delete(entity: DepartmentTypeEntity) = dao.delete(entity)
}
