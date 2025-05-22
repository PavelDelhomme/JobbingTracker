package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import com.delhomme.jobbingtrack.data.local.repository.CandidatureRepository
import kotlinx.coroutines.launch

class CandidatureViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = CandidatureRepository(JobbingTrackApp.database.candidatureDao())

    /** 1) Toutes les candidatures pour un user */
    fun allForUser(userId: String): LiveData<List<CandidatureEntity>> =
        repo.allForUser(userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<CandidatureEntity>> =
        repo.activeForUser(userId).asLiveData()

    /** 3) Archivées */
    fun archivedForUser(userId: String): LiveData<List<CandidatureEntity>> =
        repo.archivedForUser(userId).asLiveData()

    /** 4) Supprimées (corbeille) */
    fun deletedForUser(userId: String): LiveData<List<CandidatureEntity>> =
        repo.deletedForUser(userId).asLiveData()

    /** 5) Détail par id */
    fun byId(id: String, userId: String): LiveData<CandidatureEntity?> =
        repo.byId(id, userId).asLiveData()

    fun save(cand: CandidatureEntity) = viewModelScope.launch { repo.save(cand) }

    fun update(cand: CandidatureEntity) = viewModelScope.launch { repo.update(cand) }

    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }

    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }

    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }

    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }

    /** Plage de dates */
    fun between(userId: String, fromTs: Long, toTs: Long): LiveData<List<CandidatureEntity>> =
        repo.getByDateRange(userId, fromTs, toTs).asLiveData()
}
