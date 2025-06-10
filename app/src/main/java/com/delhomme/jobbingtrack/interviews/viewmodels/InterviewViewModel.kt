package com.delhomme.jobbingtrack.interviews.viewmodels

import androidx.lifecycle.*
import com.delhomme.jobbingtrack.commons.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.interviews.Interview
import com.delhomme.jobbingtrack.interviews.entities.InterviewEntity
import com.delhomme.jobbingtrack.interviews.repositories.InterviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewViewModel @Inject constructor(
    private val repo: InterviewRepository
) : ViewModel() {

    fun allForUser(userId: String): LiveData<List<InterviewWithContacts>> =
        repo.withContactsForUser(userId).asLiveData()

    fun activeForUser(userId: String): LiveData<List<Interview>> =
        repo.activeForUser(userId).asLiveData()

    fun interviewById(id: String, userId: String): LiveData<InterviewWithContacts?> =
        repo.byIdWithContacts(id, userId).asLiveData()

    fun save(interview: InterviewEntity, contactsIds: List<String>) =
        viewModelScope.launch { repo.save(interview, contactsIds) }

    fun archivedForUser(userId: String): LiveData<List<InterviewEntity>> =
        repo.archivedForUser(userId).asLiveData()

    fun getAllWithContacts(userId: String): LiveData<List<InterviewWithContacts>> =
        repo.withContactsForUser(userId).asLiveData()

    fun update(interview: InterviewEntity) = viewModelScope.launch { repo.update(interview) }
    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }
    fun entretiensBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<InterviewEntity>> =
        repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}
