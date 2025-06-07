package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.repository.ApplicationPlatformRepository
import com.delhomme.jobbingtrack.data.local.entities.ApplicationPlatformEntity
import kotlinx.coroutines.launch

class ApplicationPlatformViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ApplicationPlatformRepository(JobbingTrackApp.database.applicationPlateformDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: ApplicationPlatformEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: ApplicationPlatformEntity) = viewModelScope.launch { repo.delete(entity) }
}
