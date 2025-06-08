package com.delhomme.jobbingtrack.interviews.viewmodels



import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.interviews.entities.InterviewTypeEntity
import kotlinx.coroutines.launch

class InterviewTypeViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = InterviewTypeRepository(JobbingTrackApp.database.interviewTypeDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: InterviewTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
