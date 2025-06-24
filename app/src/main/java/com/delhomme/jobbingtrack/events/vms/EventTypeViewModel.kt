package com.delhomme.jobbingtrack.events.vms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import kotlinx.coroutines.launch


class EventTypeViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EventTypeRepository(JobbingTrackApp.database.eventTypeDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: EventTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: EventTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
