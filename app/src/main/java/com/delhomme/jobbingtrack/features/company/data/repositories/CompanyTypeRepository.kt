package com.delhomme.jobbingtrack.features.company.data.repositories

import com.delhomme.jobbingtrack.features.company.data.dao.CompanyTypeDao
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CompanyTypeRepository @Inject constructor(
    private val dao: CompanyTypeDao
) {
    val all: Flow<List<CompanyTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<CompanyTypeEntity?> = dao.getById(id)
    suspend fun save(entity: CompanyTypeEntity) = dao.save(entity)
    suspend fun delete(entity: CompanyTypeEntity) = dao.delete(entity)
}
