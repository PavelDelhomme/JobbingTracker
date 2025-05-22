package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import com.delhomme.jobbingtrack.data.local.repository.RelanceRepository
import kotlinx.coroutines.launch

class RelanceViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = RelanceRepository(JobbingTrackApp.database.relanceDao())

    /** Toutes les relances pour cet utilisateur */
    fun relancesForUser(userId: String): LiveData<List<RelanceEntity>> =
        repo.allForUser(userId).asLiveData()

    /** Détaillé par id */
    fun relanceById(id: String, userId: String): LiveData<RelanceEntity?> =
        repo.byId(id, userId).asLiveData()

    fun save(relance: RelanceEntity) = viewModelScope.launch { repo.save(relance) }
    fun update(relance: RelanceEntity) = viewModelScope.launch { repo.update(relance) }
    fun archive(ids: List<String>, userId: String)      = viewModelScope.launch { repo.archive(ids, userId) }
    fun delete(ids: List<String>, userId: String)       = viewModelScope.launch { repo.softDelete(ids, userId) }
    fun restore(ids: List<String>, userId: String)      = viewModelScope.launch { repo.restore(ids, userId) }
    fun deleteForever(ids: List<String>, userId: String)= viewModelScope.launch { repo.deleteForever(ids, userId) }
    fun clearAll(userId: String)                        = viewModelScope.launch { repo.deleteAll(userId) }

    fun relancesBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<RelanceEntity>> = repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}