package com.delhomme.jobbingtrack.data.viewmodel.FollowUp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.followup.FollowUpEntity
import com.delhomme.jobbingtrack.data.local.repository.followup.FollowUpRepository
import kotlinx.coroutines.launch

class FollowUpViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = FollowUpRepository(JobbingTrackApp.database.followUpDao())

    /** Toutes les relances pour cet utilisateur */
    fun relancesForUser(userId: String): LiveData<List<FollowUpEntity>> =
        repo.allForUser(userId).asLiveData()

    /** 1) Toutes les candidatures pour un user */
    fun allForUser(userId: String): LiveData<List<FollowUpEntity>> =
        repo.allForUser(userId).asLiveData()

    fun getAllForUser(userId: String): LiveData<List<FollowUpEntity>> {
        return repo.getAllForUser(userId)
    }

    /** Détaillé par id */
    fun relanceById(id: String, userId: String): LiveData<FollowUpEntity?> =
        repo.byId(id, userId).asLiveData()

    /** 2) Actives */
    fun activeForUser(userId: String): LiveData<List<FollowUpEntity>> =
        repo.activeForUser(userId).asLiveData()

    fun archivedForUser(userId: String): LiveData<List<FollowUpEntity>> =
        repo.archivedForUser(userId).asLiveData()

    fun save(relance: FollowUpEntity) = viewModelScope.launch { repo.save(relance) }
    fun update(relance: FollowUpEntity) = viewModelScope.launch { repo.update(relance) }
    fun archive(ids: List<String>, userId: String)      = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String)       = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String)      = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String)= viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String)                        = viewModelScope.launch { repo.deleteAll(userId) }

    fun relancesBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<FollowUpEntity>> = repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}