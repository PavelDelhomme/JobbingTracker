package com.delhomme.jobbingtrack.applications.viewmodels


import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.application.ApplicationTypeEntity
import com.delhomme.jobbingtrack.data.local.repository.application.ApplicationTypeRepository
import kotlinx.coroutines.launch

class ApplicationTypeViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ApplicationTypeRepository(JobbingTrackApp.database.applicationTypeDao())

    val all: LiveData<List<ApplicationTypeEntity>> = repo.all.asLiveData()

    fun getById(id: String): LiveData<ApplicationTypeEntity?> = repo.byId(id).asLiveData()

    fun save(entity: ApplicationTypeEntity) = viewModelScope.launch {
        repo.save(entity)
    }

    fun delete(entity: ApplicationTypeEntity) = viewModelScope.launch {
        repo.delete(entity)
    }
}
