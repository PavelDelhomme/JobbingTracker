package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.repository.EventRepository
import com.delhomme.jobbingtrack.data.local.AppDatabase
import com.delhomme.jobbingtrack.data.local.entities.EventEntity
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = EventRepository(
        JobbingTrackApp.database.eventDao()
    )

    val events: LiveData<List<EventEntity>> = repo.getAll() as LiveData<List<EventEntity>>

    fun add(event: EventEntity) = viewModelScope.launch {
        repo.insert(event)
    }

    fun remove(event: EventEntity) = viewModelScope.launch {
        repo.delete(event)
    }
}