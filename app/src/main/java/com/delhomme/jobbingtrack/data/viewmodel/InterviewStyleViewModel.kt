package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.InterviewStyleEntity
import com.delhomme.jobbingtrack.data.local.repository.InterviewStyleRepository
import kotlinx.coroutines.launch

class InterviewStyleViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = InterviewStyleRepository(JobbingTrackApp.database.interviewStyleDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: InterviewStyleEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewStyleEntity) = viewModelScope.launch { repo.delete(entity) }
}
