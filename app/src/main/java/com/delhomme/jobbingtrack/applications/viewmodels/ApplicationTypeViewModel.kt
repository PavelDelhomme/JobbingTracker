package com.delhomme.jobbingtrack.applications.viewmodels


import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.applications.entities.ApplicationTypeEntity
import com.delhomme.jobbingtrack.applications.repositories.ApplicationTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

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
