package com.delhomme.jobbingtrack.data.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.CompanyEntity
import com.delhomme.jobbingtrack.data.local.repository.CompanyRepository
import kotlinx.coroutines.launch

class CompanyViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = CompanyRepository(JobbingTrackApp.database.companyDao())

    fun allForUser(userId: String): LiveData<List<CompanyEntity>> = repo.allForUser(userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<CompanyEntity>> =
        repo.activeForUser(userId).asLiveData()

    /** 3) Archivées */
    fun archivedForUser(userId: String): LiveData<List<CompanyEntity>> =
        repo.archivedForUser(userId).asLiveData()

    fun companyById(id: String, userId: String): LiveData<CompanyEntity?> = repo.byId(id, userId).asLiveData()

    fun save(company: CompanyEntity) = viewModelScope.launch { repo.save(company) }

    fun update(company: CompanyEntity) = viewModelScope.launch { repo.update(company) }

    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }
}