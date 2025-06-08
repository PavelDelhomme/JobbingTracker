package com.delhomme.jobbingtrack.calls.viewmodels


import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.repository.call.CallTypeRepository
import kotlinx.coroutines.launch

class CallTypeViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = CallTypeRepository(JobbingTrackApp.database.callTypeDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: CallTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: CallTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
