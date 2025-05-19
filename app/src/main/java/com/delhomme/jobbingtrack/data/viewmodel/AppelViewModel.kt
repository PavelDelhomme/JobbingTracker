package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.data.local.repository.AppelRepository
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import kotlinx.coroutines.launch


class AppelViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = AppelRepository(JobbingTrackApp.database.appelDao())

    /** Expose un LiveData des entités */
    val appels: LiveData<List<AppelEntity>> = repo.appels.asLiveData()

    fun save(appel: AppelEntity) = viewModelScope.launch {
        repo.save(appel)
    }

    fun archive(id: String) = viewModelScope.launch {
        repo.archive(id)
    }

    fun delete(id: String) = viewModelScope.launch {
        repo.delete(id)
    }
}