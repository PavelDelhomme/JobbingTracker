package com.delhomme.jobbingtrack.followsup.viewmodels

import androidx.lifecycle.*
import com.delhomme.jobbingtrack.followsup.entities.FollowUpStatusEntity
import com.delhomme.jobbingtrack.followsup.repositories.FollowUpStatusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowUpStatusViewModel @Inject constructor(
    private val repo: FollowUpStatusRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: FollowUpStatusEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: FollowUpStatusEntity) = viewModelScope.launch { repo.delete(entity) }
}
