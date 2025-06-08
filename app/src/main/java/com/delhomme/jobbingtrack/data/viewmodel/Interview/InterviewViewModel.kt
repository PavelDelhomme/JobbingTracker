package com.delhomme.jobbingtrack.data.viewmodel.Interview

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.classes.interviews.Interview
import com.delhomme.jobbingtrack.data.local.entities.interview.InterviewEntity
import com.delhomme.jobbingtrack.data.local.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.data.local.repository.interview.InterviewRepository
import kotlinx.coroutines.launch

class InterviewViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = InterviewRepository(JobbingTrackApp.database.interviewDao())

    fun allForUser(userId: String): LiveData<List<InterviewWithContacts>> =
        repo.withContactsForUser(userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<Interview>> = repo.activeForUser(userId)

    fun interviewById(id: String, userId: String): LiveData<InterviewWithContacts?> = repo.byIdWithContacts(id, userId).asLiveData()

    fun save(interview: InterviewEntity, contactsIds: List<String>) = viewModelScope.launch { repo.save(interview, contactsIds) }

    fun archivedForUser(userId: String): LiveData<List<InterviewEntity>> =
        repo.archivedForUser(userId).asLiveData()

    fun getAllWithContacts(userId: String): LiveData<List<InterviewWithContacts>> = repo.withContactsForUser(userId).asLiveData()
    fun activeWithContactsForUser(userId: String): LiveData<List<InterviewWithContacts>> {
        return repo.getActiveWithContacts(userId)
    }

    /*fun activeForUserWithContacts(userId: String): LiveData<List<Interview>> =
        repo.withContactsForUser(userId)
            .map { list -> list.map { it.toInterviewDomain() } }
            .asLiveData()
    */

    fun update(interview: InterviewEntity) = viewModelScope.launch { repo.update(interview) }

    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun archiveOne(id: String, userId: String) = archive(listOf(id), userId)

    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun deleteOne(id: String, userId: String) = delete(listOf(id), userId)

    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun restoreOne(id: String, userId: String) = restore(listOf(id), userId)

    fun isDeletedForUser(userId: String): LiveData<List<InterviewEntity>> =
        repo.deletedForUser(userId).asLiveData()

    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun deleteForeverOne(id: String, userId: String) = deleteForever(listOf(id), userId)

    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }

    fun entretiensBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<InterviewEntity>> = repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}