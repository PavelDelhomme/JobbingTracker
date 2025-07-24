package com.delhomme.jobbingtrack.features.interview.data.repositories

import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewStatusDao
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStatusEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InterviewStatusRepository @Inject constructor(
    private val dao: InterviewStatusDao
) {
    val all: Flow<List<InterviewStatusEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewStatusEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<InterviewStatusEntity>> = dao.getAllForUser(userId)

    suspend fun save(interviewStatus: InterviewStatusEntity) = dao.insert(interviewStatus)
    suspend fun update(interviewStatus: InterviewStatusEntity) = dao.update(interviewStatus)
    suspend fun delete(id: String, userId: String) = dao.softDeleteById(id, userId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<InterviewStatusEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}