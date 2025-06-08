package com.delhomme.jobbingtrack.followsup.viewmodels


import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.followsup.entities.FollowUpStatusEntity
import com.delhomme.jobbingtrack.followsup.repositories.FollowUpStatusRepository
import kotlinx.coroutines.launch

class FollowUpStatusViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = FollowUpStatusRepository(JobbingTrackApp.database.followUpStatusdao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: FollowUpStatusEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: FollowUpStatusEntity) = viewModelScope.launch { repo.delete(entity) }
}
