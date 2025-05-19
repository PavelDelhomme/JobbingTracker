package com.delhomme.jobbingtrack.data.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import com.delhomme.jobbingtrack.data.local.repository.EntrepriseRepository
import kotlinx.coroutines.launch

class EntrepriseViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EntrepriseRepository(JobbingTrackApp.database.entrepriseDao())

    val entreprises: LiveData<List<EntrepriseEntity>> = repo.entreprises.asLiveData()

    fun save(entreprise: EntrepriseEntity) = viewModelScope.launch {
        repo.save(entreprise)
    }
    fun archive(id: String) = viewModelScope.launch {
        repo.archive(id)
    }
    fun delete(id: String) = viewModelScope.launch {
        repo.delete(id)
    }
}