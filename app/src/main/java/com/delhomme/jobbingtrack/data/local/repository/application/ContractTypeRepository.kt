package com.delhomme.jobbingtrack.data.local.repository.application

import com.delhomme.jobbingtrack.data.local.dao.applications.ContractTypeDao
import com.delhomme.jobbingtrack.data.local.entities.application.ContractTypeEntity
import kotlinx.coroutines.flow.Flow

class ContractTypeRepository(private val dao: ContractTypeDao) {
    val all: Flow<List<ContractTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<ContractTypeEntity?> = dao.getById(id)
    suspend fun save(entity: ContractTypeEntity) = dao.save(entity)
    suspend fun delete(entity: ContractTypeEntity) = dao.delete(entity)
}