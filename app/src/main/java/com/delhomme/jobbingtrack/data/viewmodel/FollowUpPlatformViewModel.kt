package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.repository.FollowUpPlatformRepository
import kotlinx.coroutines.launch

class FollowUpPlatformViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = FollowUpPlatformRepository(JobbingTrackApp.database.followUpPlateformDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: FollowUpPlatformEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: FollowUpPlatformEntity) = viewModelScope.launch { repo.delete(entity) }
}
