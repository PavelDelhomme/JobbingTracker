package com.delhomme.jobbingtrack.datas.repositories

import com.delhomme.jobbingtrack.datas.daos.companies.CompanyDao
import com.delhomme.jobbingtrack.datas.daos.companies.CompanyTypeDao
import com.delhomme.jobbingtrack.datas.entities.companies.CompanyEntity
import com.delhomme.jobbingtrack.datas.entities.companies.CompanyTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CompanyRepository @Inject constructor(
    private val dao: CompanyDao
) {
    fun allForUser(userId: String): Flow<List<CompanyEntity>> = dao.getAllForUser(userId)

    fun activeForUser(userId: String): Flow<List<CompanyEntity>> = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<CompanyEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<CompanyEntity>> = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<CompanyEntity?> = dao.getByIdForUser(id, userId)

    suspend fun save(entreprise: CompanyEntity)                = dao.upsert(entreprise)
    suspend fun update(entreprise: CompanyEntity)              = dao.upsert(entreprise)
    suspend fun archive(ids: List<String>, userId: String)          = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)       = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)          = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)    = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String)                           = dao.deleteAllForUser(userId)
}


class CompanyTypeRepository @Inject constructor(
    private val dao: CompanyTypeDao
) {
    val all: Flow<List<CompanyTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<CompanyTypeEntity?> = dao.getById(id)
    suspend fun save(entity: CompanyTypeEntity) = dao.save(entity)
    suspend fun delete(entity: CompanyTypeEntity) = dao.delete(entity)
}
