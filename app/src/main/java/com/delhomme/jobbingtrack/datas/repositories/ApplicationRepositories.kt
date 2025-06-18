package com.delhomme.jobbingtrack.datas.repositories

import com.delhomme.jobbingtrack.datas.daos.applications.ApplicationDao
import com.delhomme.jobbingtrack.datas.daos.applications.ApplicationPlatformDao
import com.delhomme.jobbingtrack.datas.daos.applications.ApplicationStatusDao
import com.delhomme.jobbingtrack.datas.daos.applications.ApplicationTypeDao
import com.delhomme.jobbingtrack.datas.daos.applications.ContractTypeDao
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationStatusEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationTypeEntity
import com.delhomme.jobbingtrack.datas.entities.applications.ContractTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ApplicationRepository @Inject constructor(private val dao: ApplicationDao) {
    fun allForUser(userId: String): Flow<List<ApplicationEntity>> = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<ApplicationEntity>> = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<ApplicationEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<ApplicationEntity>> = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<ApplicationEntity?> = dao.getByIdForUser(id, userId)

    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<ApplicationEntity>> = dao.getByDateRangeForUser(userId, from, to)

    suspend fun save(candidature: ApplicationEntity) = dao.upsert(candidature)
    suspend fun update(candidature: ApplicationEntity) = dao.upsert(candidature)
    suspend fun archive(ids: List<String>, userId: String) = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = dao.deleteAllForUser(userId)
}

class ApplicationStatusRepository @Inject constructor(
    private val dao: ApplicationStatusDao
) {
    val all: Flow<List<ApplicationStatusEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    suspend fun save(status: ApplicationStatusEntity) = dao.save(status)
    suspend fun delete(status: ApplicationStatusEntity) = dao.delete(status)
    fun allForUser(userId: String) = dao.getAllForUser(userId)
}

class ApplicationTypeRepository @Inject constructor(
    private val dao: ApplicationTypeDao
) {
    val all: Flow<List<ApplicationTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<ApplicationTypeEntity?> = dao.getById(id)
    suspend fun save(entity: ApplicationTypeEntity) = dao.save(entity)
    suspend fun delete(entity: ApplicationTypeEntity) = dao.delete(entity)
}


class ContractTypeRepository @Inject constructor(
    private val dao: ContractTypeDao  // Bon DAO
) {
    val all: Flow<List<ContractTypeEntity>> = dao.getAll()  // Bon type
    fun byId(id: String): Flow<ContractTypeEntity?> = dao.getById(id)  // Bon type
    suspend fun save(entity: ContractTypeEntity) = dao.save(entity)  // Bon type
    suspend fun delete(entity: ContractTypeEntity) = dao.delete(entity)  // Bon type
}

class ApplicationPlatformRepository @Inject constructor(
    private val dao: ApplicationPlatformDao
) {
    val all: Flow<List<ApplicationPlatformEntity>> = dao.getAll()
    fun byId(id: String) = dao.getById(id)
    fun allForUser(userId: String) = dao.getAllForUser(userId)
    suspend fun save(status: ApplicationPlatformEntity) = dao.save(status)
    suspend fun delete(status: ApplicationPlatformEntity) = dao.delete(status)
}
