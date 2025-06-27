package com.delhomme.jobbingtrack.applications.repo

import com.delhomme.jobbingtrack.applications.ContractTypeEntity
import com.delhomme.jobbingtrack.applications.dao.ContractTypeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ContractTypeRepository @Inject constructor(
    private val dao: ContractTypeDao  // Bon DAO
) {
    val all: Flow<List<ContractTypeEntity>> = dao.getAll()  // Bon type
    fun byId(id: String): Flow<ContractTypeEntity?> = dao.getById(id)  // Bon type
    suspend fun save(entity: ContractTypeEntity) = dao.save(entity)  // Bon type
    suspend fun delete(entity: ContractTypeEntity) = dao.delete(entity)  // Bon type
}


