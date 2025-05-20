package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import com.delhomme.jobbingtrack.data.local.repository.CandidatureRepository
import kotlinx.coroutines.launch

class CandidatureViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = CandidatureRepository(JobbingTrackApp.database.candidatureDao())

    /** Expose un LiveData des candidatures */
    val candidatures: LiveData<List<CandidatureEntity>> = repo.candidatures.asLiveData()

    fun save(cand: CandidatureEntity) = viewModelScope.launch {
        repo.save(cand)
    }
    fun archive(id: String) = viewModelScope.launch {
        repo.archive(id)
    }
    fun delete(id: String) = viewModelScope.launch {
        repo.delete(id)
    }


}
