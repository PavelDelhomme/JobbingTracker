package com.delhomme.jobbingtrack.feature.followup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FollowUpPlatformViewModel @Inject constructor(
    private val repo: FollowUpPlatformRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: FollowUpPlateformEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: FollowUpPlateformEntity) = viewModelScope.launch { repo.delete(entity) }
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
