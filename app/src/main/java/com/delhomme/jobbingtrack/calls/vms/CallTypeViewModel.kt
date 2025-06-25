package com.delhomme.jobbingtrack.calls.vms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.calls.CallTypeEntity
import com.delhomme.jobbingtrack.calls.repo.CallTypeRepository
import kotlinx.coroutines.launch


class CallTypeViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = CallTypeRepository(JobbingTrackApp.database.callTypeDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: CallTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: CallTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}