package com.delhomme.jobbingtrack.applications.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ApplicationStatusViewModel @Inject constructor(
    private val repo: ApplicationStatusRepository
) : ViewModel() {
    val all: LiveData<List<ApplicationStatusEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ApplicationStatusEntity?> = repo.byId(id).asLiveData()
    fun save(status: ApplicationStatusEntity) = viewModelScope.launch { repo.save(status) }
    fun delete(status: ApplicationStatusEntity) = viewModelScope.launch { repo.delete(status) }
}