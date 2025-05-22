package com.delhomme.jobbingtrack.data.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import com.delhomme.jobbingtrack.data.local.repository.EntrepriseRepository
import kotlinx.coroutines.launch

class EntrepriseViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EntrepriseRepository(JobbingTrackApp.database.entrepriseDao())

    fun entreprisesForUser(userId: String): LiveData<List<EntrepriseEntity>> = repo.allForUser(userId).asLiveData()

    fun entrepriseById(id: String, userId: String): LiveData<EntrepriseEntity?> = repo.byId(id, userId).asLiveData()

    fun save(entreprise: EntrepriseEntity) = viewModelScope.launch { repo.save(entreprise) }

    fun update(entreprise: EntrepriseEntity) = viewModelScope.launch { repo.update(entreprise) }

    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }
}