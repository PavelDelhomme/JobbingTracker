package com.delhomme.jobbingtrack.features.interview.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStyleEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewTypeEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.features.interview.data.repositories.InterviewRepository
import com.delhomme.jobbingtrack.features.interview.data.repositories.InterviewStyleRepository
import com.delhomme.jobbingtrack.features.interview.data.repositories.InterviewTypeRepository
import com.delhomme.jobbingtrack.features.interview.domain.model.Interview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InterviewViewModel @Inject constructor(
    private val repo: InterviewRepository,
    private val styleRepository: InterviewStyleRepository,
    private val typeRepository: InterviewTypeRepository,
    private val userId: String
) : ViewModel() {
    val stylesFlow: Flow<List<InterviewStyleEntity>> = styleRepository.all
    val typesFlow: Flow<List<InterviewTypeEntity>> = typeRepository.all
    val allTypes: LiveData<List<InterviewTypeEntity>> = typeRepository.all.asLiveData()
    val allStyles: LiveData<List<InterviewStyleEntity>> = styleRepository.all.asLiveData()

    val activeInterviews: LiveData<List<Interview>> =
        repo.activeForUser(userId, stylesFlow, typesFlow).asLiveData()

    fun allForUser(userId: String): LiveData<List<InterviewWithContacts>> =
        repo.withContactsForUser(userId).asLiveData()

    fun interviewById(id: String, userId: String): LiveData<InterviewWithContacts?> =
        repo.byIdWithContacts(id, userId).asLiveData()

    fun save(interview: InterviewEntity, contactsIds: List<String>) =
        viewModelScope.launch { repo.save(interview, contactsIds) }

    fun archivedForUser(userId: String): LiveData<List<InterviewEntity>> =
        repo.archivedForUser(userId).asLiveData()

    fun getAllWithContacts(userId: String): LiveData<List<InterviewWithContacts>> =
        repo.withContactsForUser(userId).asLiveData()
    fun getAllActiveWithContacts(userId: String): LiveData<List<InterviewWithContacts>> =
        repo.getAllActiveWithContacts(userId).asLiveData()

    fun update(interview: InterviewEntity) = viewModelScope.launch { repo.update(interview) }
    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }
    fun entretiensBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<InterviewEntity>> =
        repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}

