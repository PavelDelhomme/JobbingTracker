package com.delhomme.jobbingtrack.datas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.datas.daos.followsups.FollowUpDao
import com.delhomme.jobbingtrack.datas.daos.followsups.FollowUpWithContacts
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpPlateformEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpStatusEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpTypeEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewWithContacts
import com.delhomme.jobbingtrack.datas.repositories.FollowUpPlatformRepository
import com.delhomme.jobbingtrack.datas.repositories.FollowUpRepository
import com.delhomme.jobbingtrack.datas.repositories.FollowUpStatusRepository
import com.delhomme.jobbingtrack.datas.repositories.FollowUpTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class FollowUpViewModel @Inject constructor(
    private val repo: FollowUpRepository,
    private val typeRepository: FollowUpTypeRepository,
    private val statusRepository: FollowUpStatusRepository,
    private val platformRepository: FollowUpPlatformRepository,
    private val dao: FollowUpDao
) : ViewModel() {
    fun allForUser(userId: String): LiveData<List<FollowUpEntity>> = repo.allForUser(userId).asLiveData()
    fun relanceById(id: String, userId: String): LiveData<FollowUpEntity?> = repo.byId(id, userId).asLiveData()
    fun activeForUser(userId: String): LiveData<List<FollowUpEntity>> = repo.activeForUser(userId).asLiveData()
    fun archivedForUser(userId: String): LiveData<List<FollowUpEntity>> = repo.archivedForUser(userId).asLiveData()

    fun getAllActiveWithContacts(userId: String): LiveData<List<FollowUpWithContacts>> =
        repo.getAllActiveWithContacts(userId).asLiveData()

    // Récupérer les IDs des contacts pour un suivi
    suspend fun getContactIdsForFollowUp(followUpId: String): List<String> {
        return repo.getContactIdsForFollowUp(followUpId)
    }

    // Récupérer tous les types de suivi
    fun getAllFollowUpTypes(): LiveData<List<FollowUpTypeEntity>> =
        repo.getAllFollowUpTypes().asLiveData()

    // Récupérer tous les statuts de suivi (qui remplacent les "responses")
    fun getAllFollowUpResponses(): LiveData<List<FollowUpStatusEntity>> {
        return repo.getAllFollowUpResponses().asLiveData()
    }

    // Sauvegarder un suivi avec ses contacts associés
    fun save(followUp: FollowUpEntity, contactIds: List<String>) {
        viewModelScope.launch {
            repo.save(followUp, contactIds)
        }
    }
    fun update(entity: FollowUpEntity) = viewModelScope.launch { repo.update(entity) }
    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }
    fun relancesBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<FollowUpEntity>> =
        repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}



@HiltViewModel
class FollowUpStatusViewModel @Inject constructor(
    private val repo: FollowUpStatusRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: FollowUpStatusEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: FollowUpStatusEntity) = viewModelScope.launch { repo.delete(entity) }
}

@HiltViewModel
class FollowUpTypeViewModel @Inject constructor(
    private val repo: FollowUpTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: FollowUpTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: FollowUpTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}

@HiltViewModel
class FollowUpPlatformViewModel @Inject constructor(
    private val repo: FollowUpPlatformRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: FollowUpPlateformEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: FollowUpPlateformEntity) = viewModelScope.launch { repo.delete(entity) }
}
