package com.delhomme.jobbingtrack.interviews.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.delhomme.jobbingtrack.commons.entities.InterviewContactCrossRef
import com.delhomme.jobbingtrack.commons.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.interviews.Interview
import com.delhomme.jobbingtrack.interviews.dao.InterviewDao
import com.delhomme.jobbingtrack.interviews.entities.InterviewEntity
import com.delhomme.jobbingtrack.interviews.utils.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InterviewRepository(private val dao: InterviewDao) {

    fun allForUser(userId: String): Flow<List<InterviewWithContacts>>     = dao.getAllActiveForUser(userId)
    fun withContactsForUser(userId: String): Flow<List<InterviewWithContacts>> = dao.getAllWithContactsForUser(userId)
    fun archivedForUser(userId: String): Flow<List<InterviewEntity>>            = dao.getArchivedForUser(userId)
    fun deletedForUser(userId: String): Flow<List<InterviewEntity>>             = dao.getDeletedForUser(userId)
    fun byIdWithContacts(id: String, userId: String): Flow<InterviewWithContacts?> =
        dao.getByIdActiveWithContacts(id, userId)
    //fun activeForUser(userId: String): Flow<List<EntretienEntity>>      = dao.getAllActiveForUser(userId)
    fun getActiveWithContacts(userId: String): LiveData<List<InterviewWithContacts>> {
        return dao.getActiveWithContacts(userId)
    }

    fun activeForUser(userId: String): LiveData<List<Interview>> =
        withContactsForUser(userId)
            .map { list -> list.map { it.toDomain() } }
            .asLiveData()

    fun getByDateRange(userId: String, from: Long, to: Long): Flow<List<InterviewEntity>> = dao.getByDateRangeForUser(userId, from, to)

    suspend fun save(entretien: InterviewEntity, contactIds: List<String>) {
        dao.upsert(entretien)
        dao.clearContactsFor(entretien.id)
        contactIds.forEach { dao.insertCrossRef(InterviewContactCrossRef(entretien.id, it)) }
    }
    suspend fun update(entretien: InterviewEntity)                            = dao.update(entretien)
    suspend fun archive(ids: List<String>, userId: String)                   = dao.archive(ids, userId)
    suspend fun softDelete(ids: List<String>, userId: String)                = dao.softDelete(ids, userId)
    suspend fun restore(ids: List<String>, userId: String)                   = dao.restore(ids, userId)
    suspend fun deleteForever(ids: List<String>, userId: String)             = dao.deleteForever(ids, userId)
    suspend fun deleteAll(userId: String)                                    = dao.deleteAllForUser(userId)
}