package com.delhomme.jobbingtrack.data.viewmodel.Profil

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.cv.ProjectEntity
import com.delhomme.jobbingtrack.data.local.repository.cv.ProjectRepository
import kotlinx.coroutines.launch

class ProjectViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ProjectRepository(JobbingTrackApp.database.projectDao())

    val all: LiveData<List<ProjectEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<ProjectEntity?> = repo.byId(id).asLiveData()

    fun save(entity: ProjectEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: ProjectEntity) = viewModelScope.launch { repo.delete(entity) }
}
