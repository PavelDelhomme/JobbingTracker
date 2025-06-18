package com.delhomme.jobbingtrack.datas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewStatusEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewStyleEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewTypeEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewWithContacts
import com.delhomme.jobbingtrack.datas.enumes.InterviewStyle
import com.delhomme.jobbingtrack.datas.enumes.InterviewType
import com.delhomme.jobbingtrack.datas.mappers.toDomain
import com.delhomme.jobbingtrack.datas.models.Interview
import com.delhomme.jobbingtrack.datas.repositories.InterviewRepository
import com.delhomme.jobbingtrack.datas.repositories.InterviewStatusRepository
import com.delhomme.jobbingtrack.datas.repositories.InterviewStyleRepository
import com.delhomme.jobbingtrack.datas.repositories.InterviewTypeRepository
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
    val stylesFlow: Flow<List<InterviewStyleEntity>> = styleRepository.all
    val typesFlow: Flow<List<InterviewTypeEntity>> = typeRepository.all

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



@HiltViewModel
class InterviewStatusViewModel @Inject constructor(
    private val repo: InterviewStatusRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: InterviewStatusEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewStatusEntity) = viewModelScope.launch { repo.delete(entity) }
    fun archive(id: String) = viewModelScope.launch { repo.archive(id) }
    fun restore(id: String) = viewModelScope.launch { repo.restore(id) }
}



@HiltViewModel
class InterviewStyleViewModel @Inject constructor(
    private val repo: InterviewStyleRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: InterviewStyleEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewStyleEntity) = viewModelScope.launch { repo.delete(entity) }
}



@HiltViewModel
class InterviewTypeViewModel @Inject constructor(
    private val repo: InterviewTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: InterviewTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
