package com.delhomme.jobbingtrack.datas.repositories

import com.delhomme.jobbingtrack.datas.daos.followsups.FollowUpDao
import com.delhomme.jobbingtrack.datas.daos.followsups.FollowUpPlatformDao
import com.delhomme.jobbingtrack.datas.daos.followsups.FollowUpStatusDao
import com.delhomme.jobbingtrack.datas.daos.followsups.FollowUpTypeDao
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpPlateformEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpStatusEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FollowUpPlatformRepository @Inject constructor(
    private val dao: FollowUpPlatformDao
) {
    val all: Flow<List<FollowUpPlateformEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpPlateformEntity?> = dao.getById(id)
    suspend fun save(entity: FollowUpPlateformEntity) = dao.save(entity)
    suspend fun delete(entity: FollowUpPlateformEntity) = dao.delete(entity)
}


class FollowUpRepository @Inject constructor(
    private val dao: FollowUpDao
) {
    fun allForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getAllForUser(userId)
    fun activeForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getAllActiveForUser(userId)
    fun archivedForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<FollowUpEntity>> = dao.getDeletedForUser(userId)
    fun byId(id: String, userId: String): Flow<FollowUpEntity?> = dao.getByIdForUser(id, userId)
    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<FollowUpEntity>> = dao.getByDateRangeForUser(userId, from, to)

    suspend fun save(entity: FollowUpEntity) = dao.upsert(entity)
    suspend fun update(entity: FollowUpEntity) = dao.update(entity)
    suspend fun archive(ids: List<String>, userId: String) = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String) = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String) = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String) = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String) = dao.deleteAllForUser(userId)
}



class FollowUpStatusRepository @Inject constructor(
    private val dao: FollowUpStatusDao
) {
    val all: Flow<List<FollowUpStatusEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpStatusEntity?> = dao.getById(id)
    suspend fun save(entity: FollowUpStatusEntity) = dao.save(entity)
    suspend fun delete(entity: FollowUpStatusEntity) = dao.delete(entity)
}



class FollowUpTypeRepository@Inject constructor(
    private val dao: FollowUpTypeDao
) {
    val all: Flow<List<FollowUpTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<FollowUpTypeEntity?> = dao.getById(id)
    suspend fun save(entity: FollowUpTypeEntity) = dao.save(entity)
    suspend fun delete(entity: FollowUpTypeEntity) = dao.delete(entity)
}