package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.ApplicationEntity
import com.delhomme.jobbingtrack.data.local.repository.ApplicationRepository
import kotlinx.coroutines.launch

class ApplicationViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ApplicationRepository(JobbingTrackApp.database.applicationDao())

    /** 1) Toutes les candidatures pour un user */
    fun allForUser(userId: String): LiveData<List<ApplicationEntity>> =
        repo.allForUser(userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<ApplicationEntity>> =
        repo.activeForUser(userId).asLiveData()

    /** 3) Archivées */
    fun archivedForUser(userId: String): LiveData<List<ApplicationEntity>> =
        repo.archivedForUser(userId).asLiveData()

    /** 4) Supprimées (corbeille) */
    fun deletedForUser(userId: String): LiveData<List<ApplicationEntity>> =
        repo.deletedForUser(userId).asLiveData()

    /** 5) Détail par id */
    fun byId(id: String, userId: String): LiveData<ApplicationEntity?> =
        repo.byId(id, userId).asLiveData()

    fun save(cand: ApplicationEntity) = viewModelScope.launch { repo.save(cand) }

    fun update(cand: ApplicationEntity) = viewModelScope.launch { repo.update(cand) }

    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }

    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }

    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }

    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }

    /** Plage de dates */
    fun between(userId: String, fromTs: Long, toTs: Long): LiveData<List<ApplicationEntity>> =
        repo.getByDateRange(userId, fromTs, toTs).asLiveData()
}
