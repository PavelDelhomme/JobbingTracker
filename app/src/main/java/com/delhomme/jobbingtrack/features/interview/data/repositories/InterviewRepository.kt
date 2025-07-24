package com.delhomme.jobbingtrack.features.interview.data.repositories

import androidx.lifecycle.LiveData
import com.delhomme.jobbingtrack.features.interview.data.dao.InterviewDao
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewWithRelations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InterviewRepository @Inject constructor(
    private val interviewDao: InterviewDao
) {
    fun getAll(): Flow<List<InterviewEntity>> = interviewDao.getAll()

    suspend fun getWithRelations(id: String): InterviewWithRelations = interviewDao.getWithRelations(id)

    fun getAllActiveForUser(userId: String): Flow<List<InterviewWithContacts>> = interviewDao.getAllActiveForUser(userId)

    fun getActiveWithContacts(userId: String): LiveData<List<InterviewWithContacts>> = interviewDao.getActiveWithContacts(userId)

    fun getArchivedForUser(userId: String): Flow<List<InterviewEntity>> = interviewDao.getArchivedForUser(userId)

    fun getDeletedForUser(userId: String): Flow<List<InterviewEntity>> = interviewDao.getDeletedForUser(userId)

    fun getByIdForUser(id: String, userId: String): Flow<InterviewEntity?> = interviewDao.getByIdForUser(id, userId)

    fun getAllWithContactsForUser(userId: String): Flow<List<InterviewWithContacts>> = interviewDao.getAllWithContactsForUser(userId)

    fun getByIdActiveWithContacts(id: String, userId: String): Flow<InterviewWithContacts?> = interviewDao.getByIdActiveWithContacts(id, userId)

    suspend fun save(interview: InterviewEntity): Long = interviewDao.insert(interview)

    suspend fun update(interview: InterviewEntity) = interviewDao.update(interview)

    suspend fun archive(ids: List<String>, userId: String) = interviewDao.archive(ids, userId)

    suspend fun softDelete(ids: List<String>, userId: String) = interviewDao.softDelete(ids, userId)

    suspend fun restore(ids: List<String>, userId: String) = interviewDao.restore(ids, userId)

    suspend fun deleteForever(ids: List<String>, userId: String) = interviewDao.deleteForever(ids, userId)

    suspend fun deleteAll(userId: String) = interviewDao.deleteAllForUser(userId)

    fun getInterviewWithContacts(id: String, userId: String): Flow<InterviewWithContacts?> = interviewDao.getInterviewWithContacts(id, userId)

    fun getAllActiveWithContacts(userId: String): Flow<List<InterviewWithContacts>> = interviewDao.getAllActiveWithContacts(userId)

    suspend fun getUpdatedSince(timestamp: Long, userId: String): List<InterviewEntity> = interviewDao.getUpdatedSince(timestamp, userId)

    suspend fun updateSyncTimestamp(ids: List<String>, syncTime: Long) = interviewDao.updateSyncTimestamp(ids, syncTime)
}