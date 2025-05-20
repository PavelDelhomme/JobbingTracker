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

    val relances: LiveData<List<RelanceEntity>> = repo.relances.asLiveData()

    fun save(relance: RelanceEntity) = viewModelScope.launch {
        repo.save(relance)
    }

    fun archive(id: String) = viewModelScope.launch {
        repo.archive(id)
    }

    fun delete(id: String) = viewModelScope.launch {
        repo.delete(id)
    }
}