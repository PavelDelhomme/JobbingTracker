package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.data.local.repository.AppelRepository
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.utils.mappers.toDomain as toAppelDomain
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import kotlinx.coroutines.launch


class AppelViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = AppelRepository(JobbingTrackApp.database.appelDao())

    /** Toutes les relances pour cet utilisateur */
    fun appelsForUser(userId: String): LiveData<List<AppelEntity>> =
        repo.allForUser(userId).asLiveData()

    fun archivedForUser(userId: String): LiveData<List<AppelEntity>> =
        repo.archivedForUser(userId).asLiveData()

    /** 1) Toutes les candidatures pour un user */
    fun allForUser(userId: String): LiveData<List<AppelEntity>> =
        repo.allForUser(userId).asLiveData()

    /** Détaillé par id */
    fun appelById(id: String, userId: String): LiveData<AppelEntity?> =
        repo.byId(id, userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<AppelEntity>> =
        repo.activeForUser(userId).asLiveData()

    fun save(appel: AppelEntity) = viewModelScope.launch { repo.save(appel) }
    fun update(appel: AppelEntity) = viewModelScope.launch { repo.update(appel) }
    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }

    fun appelsBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<AppelEntity>> = repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}