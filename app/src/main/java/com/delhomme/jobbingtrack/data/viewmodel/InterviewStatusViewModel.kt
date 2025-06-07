package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.InterviewStatusEntity
import com.delhomme.jobbingtrack.data.local.repository.InterviewStatusRepository
import kotlinx.coroutines.launch

class InterviewStatusViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = InterviewStatusRepository(JobbingTrackApp.database.interviewStatusDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: InterviewStatusEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewStatusEntity) = viewModelScope.launch { repo.delete(entity) }
}
