package com.delhomme.jobbingtrack.features.company.data.repositories

import com.delhomme.jobbingtrack.features.company.data.dao.CompanyTypeDao
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyTypeRepository @Inject constructor(
    private val companyTypeDao: CompanyTypeDao
) {
    fun getAll(): Flow<List<CompanyTypeEntity>> = companyTypeDao.getAll()

    fun getById(id: String): Flow<CompanyTypeEntity?> = companyTypeDao.getById(id)

    fun getAllForUser(userId: String): Flow<List<CompanyTypeEntity>> = companyTypeDao.getAllForUser(userId)

    suspend fun insert(companyType: CompanyTypeEntity): Long = companyTypeDao.insert(companyType)

    suspend fun update(companyType: CompanyTypeEntity) = companyTypeDao.update(companyType)

    suspend fun delete(companyType: CompanyTypeEntity) = companyTypeDao.delete(companyType)

    suspend fun softDelete(id: String, userId: String) =
        companyTypeDao.softDeleteById(id, userId, System.currentTimeMillis())

    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<CompanyTypeEntity> =
        companyTypeDao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long) =
        companyTypeDao.updateSyncTimestamp(ids, syncTime)
}