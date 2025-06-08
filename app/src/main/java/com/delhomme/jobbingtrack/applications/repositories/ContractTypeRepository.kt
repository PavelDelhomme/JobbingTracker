package com.delhomme.jobbingtrack.applications.repositories


import com.delhomme.jobbingtrack.applications.dao.ContractTypeDao
import com.delhomme.jobbingtrack.applications.entities.ContractTypeEntity
import kotlinx.coroutines.flow.Flow

class ContractTypeRepository(private val dao: ContractTypeDao) {
    val all: Flow<List<ContractTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<ContractTypeEntity?> = dao.getById(id)
    suspend fun save(entity: ContractTypeEntity) = dao.save(entity)
    suspend fun delete(entity: ContractTypeEntity) = dao.delete(entity)
}