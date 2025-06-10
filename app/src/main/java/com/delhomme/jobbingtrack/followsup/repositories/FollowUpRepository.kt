package com.delhomme.jobbingtrack.followsup.repositories

import com.delhomme.jobbingtrack.followsup.dao.FollowUpDao
import com.delhomme.jobbingtrack.followsup.entities.FollowUpEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

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
