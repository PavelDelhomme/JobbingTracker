package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.EntretienEntity
import com.delhomme.jobbingtrack.data.local.repository.EntretienRepository
import kotlinx.coroutines.launch

class EntretienViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EntretienRepository(JobbingTrackApp.database.entretienDao())

    val entretiens = repo.entretiensWithContacts.asLiveData()

    fun save(entretien: EntretienEntity, contactIds: List<String>) = viewModelScope.launch { repo.save(entretien, contactIds) }

    fun archive(id: String) = viewModelScope.launch { repo.archive(id) }

    fun delete(id: String) = viewModelScope.launch { repo.delete(id) }
}