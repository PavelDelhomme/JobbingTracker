package com.delhomme.jobbingtrack.applications.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.applications.entities.ApplicationPlatformEntity
import com.delhomme.jobbingtrack.applications.repositories.ApplicationPlatformRepository
import kotlinx.coroutines.launch

class ApplicationPlatformViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ApplicationPlatformRepository(JobbingTrackApp.database.applicationPlateformDao())

    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()

    fun save(entity: ApplicationPlatformEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: ApplicationPlatformEntity) = viewModelScope.launch { repo.delete(entity) }
}
