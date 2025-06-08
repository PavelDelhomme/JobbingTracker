package com.delhomme.jobbingtrack.data.viewmodel.Application

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.application.ApplicationStatusEntity
import com.delhomme.jobbingtrack.data.local.repository.application.ApplicationStatusRepository
import kotlinx.coroutines.launch

class ApplicationStatusViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = JobbingTrackApp.database.applicationStatusDao()
    private val repo = ApplicationStatusRepository(dao)

    val all: LiveData<List<ApplicationStatusEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ApplicationStatusEntity?> = repo.byId(id).asLiveData()

    fun save(status: ApplicationStatusEntity) = viewModelScope.launch { repo.save(status) }
    fun delete(status: ApplicationStatusEntity) = viewModelScope.launch { repo.delete(status) }
}