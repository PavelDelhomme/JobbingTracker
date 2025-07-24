package com.delhomme.jobbingtrack.features.call.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.features.call.data.entities.CallTypeEntity
import com.delhomme.jobbingtrack.features.call.data.repositories.CallTypeRepository
import kotlinx.coroutines.launch

class CallTypeViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = CallTypeRepository(JobbingTrackApp.database.callTypeDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: CallTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun update(entity: CallTypeEntity) = viewModelScope.launch { repo.update(entity) }
    fun delete(entity: CallTypeEntity) = viewModelScope.launch {
        repo.delete(entity.id, entity.userId)
    }
}