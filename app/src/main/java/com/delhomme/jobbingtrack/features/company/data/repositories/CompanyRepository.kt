package com.delhomme.jobbingtrack.features.company.data.repositories

import com.delhomme.jobbingtrack.features.company.data.dao.CompanyDao
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyRepository @Inject constructor(
    private val companyDao: CompanyDao
) {
    fun allForUser(userId: String): Flow<List<CompanyEntity>> = companyDao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<CompanyEntity>> = companyDao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<CompanyEntity>> = companyDao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<CompanyEntity>> = companyDao.getDeletedForUser(userId)

    fun byId(id: String, userId: String): Flow<CompanyEntity?> = companyDao.getByIdForUser(id, userId)

    suspend fun save(company: CompanyEntity): Long = companyDao.insert(company)
    suspend fun update(company: CompanyEntity) = companyDao.update(company)
    suspend fun archive(ids: List<String>, userId: String) = companyDao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = companyDao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = companyDao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = companyDao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = companyDao.deleteAllForUser(userId)

    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<CompanyEntity> =
        companyDao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long) =
        companyDao.updateSyncTimestamp(ids, syncTime)
}