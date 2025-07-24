package com.delhomme.jobbingtrack.features.interview.data.repositories

import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewTypeDao
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InterviewTypeRepository @Inject constructor(
    private val dao: InterviewTypeDao
) {
    val all: Flow<List<InterviewTypeEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewTypeEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<InterviewTypeEntity>> = dao.getAllForUser(userId)

    suspend fun save(interviewType: InterviewTypeEntity) = dao.insert(interviewType)
    suspend fun update(interviewType: InterviewTypeEntity) = dao.update(interviewType)
    suspend fun delete(id: String, userId: String) = dao.softDeleteById(id, userId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<InterviewTypeEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}