package com.delhomme.jobbingtrack.companies.repo

import com.delhomme.jobbingtrack.companies.CompanyTypeEntity
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
