package com.delhomme.jobbingtrack.features.application.data.repositories

import com.delhomme.jobbingtrack.features.application.data.dao.ContractTypeDao
import com.delhomme.jobbingtrack.features.application.data.entities.ContractTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContractTypeRepository @Inject constructor(
    private val contractTypeDao: ContractTypeDao
) {
    fun getAll(): Flow<List<ContractTypeEntity>> = contractTypeDao.getAll()

    fun getById(id: String): Flow<ContractTypeEntity?> = contractTypeDao.getById(id)

    fun getAllForUser(userId: String): Flow<List<ContractTypeEntity>> = contractTypeDao.getAllForUser(userId)

    suspend fun insert(contractType: ContractTypeEntity): Long = contractTypeDao.insert(contractType)

    suspend fun update(contractType: ContractTypeEntity) = contractTypeDao.update(contractType)

    suspend fun delete(contractType: ContractTypeEntity) = contractTypeDao.delete(contractType)

    suspend fun softDelete(id: String, userId: String) =
        contractTypeDao.softDeleteById(id, userId, System.currentTimeMillis())

    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<ContractTypeEntity> =
        contractTypeDao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long) =
        contractTypeDao.updateSyncTimestamp(ids, syncTime)
}