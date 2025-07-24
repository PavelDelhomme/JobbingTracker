package com.delhomme.jobbingtrack.features.interview.data.repositories

import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewStyleDao
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStyleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InterviewStyleRepository @Inject constructor(
    private val dao: InterviewStyleDao
) {
    val all: Flow<List<InterviewStyleEntity>> = dao.getAll()
    fun byId(id: String): Flow<InterviewStyleEntity?> = dao.getById(id)
    fun allForUser(userId: String): Flow<List<InterviewStyleEntity>> = dao.getAllForUser(userId)

    suspend fun save(interviewStyle: InterviewStyleEntity) = dao.insert(interviewStyle)
    suspend fun update(interviewStyle: InterviewStyleEntity) = dao.update(interviewStyle)
    suspend fun delete(id: String, userId: String) = dao.softDeleteById(id, userId)

    // Méthodes pour la synchronisation
    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<InterviewStyleEntity> =
        dao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, timestamp: Long) =
        dao.updateSyncTimestamp(ids, timestamp)
}