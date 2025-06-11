package com.delhomme.jobbingtrack.interviews.viewmodels

import androidx.lifecycle.*
import com.delhomme.jobbingtrack.commons.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.interviews.Interview
import com.delhomme.jobbingtrack.interviews.entities.InterviewEntity
import com.delhomme.jobbingtrack.interviews.enumes.InterviewStyle
import com.delhomme.jobbingtrack.interviews.enumes.InterviewType
import com.delhomme.jobbingtrack.interviews.repositories.InterviewRepository
import com.delhomme.jobbingtrack.interviews.repositories.InterviewStyleRepository
import com.delhomme.jobbingtrack.interviews.repositories.InterviewTypeRepository
import com.delhomme.jobbingtrack.interviews.utils.mappers.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewViewModel @Inject constructor(
    private val repo: InterviewRepository,
    private val styleRepository: InterviewStyleRepository,
    private val typeRepository: InterviewTypeRepository,
    private val userId: String
) : ViewModel() {
    val stylesFlow = styleRepository.all
    val typesFlow = typeRepository.all

    val activeInterviews: LiveData<List<Interview>> =
        repo.activeForUser(userId, stylesFlow, typesFlow).asLiveData()

    fun allForUser(userId: String): LiveData<List<InterviewWithContacts>> =
        repo.withContactsForUser(userId).asLiveData()
    fun activeForUser(
        userId: String,
        stylesFlow: Flow<List<InterviewStyle>>,
        typesFlow: Flow<List<InterviewType>>
    ): Flow<List<Interview>> =
        combine(
            repo.withContactsForUser(userId),
            stylesFlow,
            typesFlow
        ) { interviewsWithContacts, styles, types ->
            interviewsWithContacts.map { iwc ->
                val style = styles.find { it.id == iwc.interview.styleId }
                val type = types.find { it.id == iwc.interview.typeId }
                iwc.toDomain(style, type)
            }
        }

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
