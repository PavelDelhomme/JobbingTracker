package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.EventEntity
import com.delhomme.jobbingtrack.data.local.repository.EventRepository
import kotlinx.coroutines.launch

class EventViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EventRepository(JobbingTrackApp.database.eventDao())

    /** LiveData exposant la liste des événements */
    val events: LiveData<List<EventEntity>> = repo
        .eventsFlow
        .asLiveData()

    /** Sauvegarde (insert ou update) */
    fun save(event: EventEntity) = viewModelScope.launch {
        repo.save(event)
    }

    /** Archive */
    fun archive(id: String, userId: String) = viewModelScope.launch {
        repo.archive(id, userId)
    }

    /** Supprime “logiquement” */
    fun delete(id: String, userId: String) = viewModelScope.launch {
        repo.softDelete(id, userId)
    }

    /** Supprimer définitivement */
    fun deleteForever(id: String, userId: String) = viewModelScope.launch {
        repo.deleteForever(id, userId)
    }
}
