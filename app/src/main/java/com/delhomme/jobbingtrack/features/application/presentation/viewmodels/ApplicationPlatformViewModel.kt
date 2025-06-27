package com.delhomme.jobbingtrack.features.application.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ApplicationPlatformViewModel @Inject constructor(
    private val repo: ApplicationPlatformRepository
) : ViewModel() {
    val all: LiveData<List<ApplicationPlatformEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ApplicationPlatformEntity?> = repo.byId(id).asLiveData()
    fun save(entity: ApplicationPlatformEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: ApplicationPlatformEntity) = viewModelScope.launch { repo.delete(entity) }
}