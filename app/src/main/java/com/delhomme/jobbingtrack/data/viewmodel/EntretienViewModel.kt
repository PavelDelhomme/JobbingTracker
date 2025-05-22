package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.EntretienEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.data.local.repository.EntretienRepository
import kotlinx.coroutines.launch

class EntretienViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EntretienRepository(JobbingTrackApp.database.entretienDao())

    fun entretiensForUser(userId: String): LiveData<List<EntretienEntity>> = repo.allForUser(userId).asLiveData()

    fun entretienById(id: String, userId: String): LiveData<EntretienWithContacts?> = repo.byIdWithContacts(id, userId).asLiveData()

    fun save(entretien: EntretienEntity, contactsIds: List<String>) = viewModelScope.launch { repo.save(entretien, contactsIds) }

    fun update(entretien: EntretienEntity) = viewModelScope.launch { repo.update(entretien) }

    fun archive(ids: List<String>, userId: String) = viewModelScope.launch { repo.archive(ids, userId) }

    fun delete(ids: List<String>, userId: String) = viewModelScope.launch { repo.softDelete(ids, userId) }

    fun restore(ids: List<String>, userId: String) = viewModelScope.launch { repo.restore(ids, userId) }

    fun deleteForever(ids: List<String>, userId: String) = viewModelScope.launch { repo.deleteForever(ids, userId) }

    fun clearAll(userId: String) = viewModelScope.launch { repo.deleteAll(userId) }

    fun entretiensBetween(userId: String, fromTimestamp: Long, toTimestamp: Long): LiveData<List<EntretienEntity>> = repo.getByDateRange(userId, fromTimestamp, toTimestamp).asLiveData()
}