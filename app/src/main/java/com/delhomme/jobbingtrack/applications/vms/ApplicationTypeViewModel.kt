package com.delhomme.jobbingtrack.applications.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.applications.ApplicationTypeEntity
import com.delhomme.jobbingtrack.applications.repo.ApplicationTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ApplicationTypeViewModel @Inject constructor(
    private val repo: ApplicationTypeRepository
) : ViewModel() {

    val all: LiveData<List<ApplicationTypeEntity>> = repo.all.asLiveData()

    fun getById(id: String): LiveData<ApplicationTypeEntity?> = repo.byId(id).asLiveData()

    fun save(entity: ApplicationTypeEntity) = viewModelScope.launch {
        repo.save(entity)
    }

    fun delete(entity: ApplicationTypeEntity) = viewModelScope.launch {
        repo.delete(entity)
    }
}