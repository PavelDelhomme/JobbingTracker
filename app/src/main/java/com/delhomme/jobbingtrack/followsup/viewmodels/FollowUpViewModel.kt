package com.delhomme.jobbingtrack.followsup.viewmodels

import androidx.lifecycle.*
import com.delhomme.jobbingtrack.followsup.entities.FollowUpEntity
import com.delhomme.jobbingtrack.followsup.repositories.FollowUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowUpViewModel @Inject constructor(
    private val repo: FollowUpRepository
) : ViewModel() {
    fun allForUser(userId: String): LiveData<List<FollowUpEntity>> = repo.allForUser(userId).asLiveData()
    fun relanceById(id: String, userId: String): LiveData<FollowUpEntity?> = repo.byId(id, userId).asLiveData()
    fun activeForUser(userId: String): LiveData<List<FollowUpEntity>> = repo.activeForUser(userId).asLiveData()
    fun archivedForUser(userId: String): LiveData<List<FollowUpEntity>> = repo.archivedForUser(userId).asLiveData()
    fun save(entity: FollowUpEntity) = viewModelScope.launch { repo.save(entity) }
    fun update(entity: FollowUpEntity) = viewModelScope.launch { repo.update(entity) }
    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }
    fun relancesBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<FollowUpEntity>> =
        repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}
