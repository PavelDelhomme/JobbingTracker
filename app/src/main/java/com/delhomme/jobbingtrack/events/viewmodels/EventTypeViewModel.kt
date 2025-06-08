package com.delhomme.jobbingtrack.events.viewmodels


import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.event.EventTypeEntity
import com.delhomme.jobbingtrack.data.local.repository.event.EventTypeRepository
import kotlinx.coroutines.launch

class EventTypeViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EventTypeRepository(JobbingTrackApp.database.eventTypeDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: EventTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: EventTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
