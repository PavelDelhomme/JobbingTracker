package com.delhomme.jobbingtrack.contacts.repo

import com.delhomme.jobbingtrack.contacts.DepartmentTypeEntity
import com.delhomme.jobbingtrack.contacts.dao.DepartmentTypeDao
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
