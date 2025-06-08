package com.delhomme.jobbingtrack.cv.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import kotlinx.coroutines.launch


class ProjectViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ProjectRepository(JobbingTrackApp.database.projectDao())

    val all: LiveData<List<ProjectEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<ProjectEntity?> = repo.byId(id).asLiveData()

    fun save(entity: ProjectEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: ProjectEntity) = viewModelScope.launch { repo.delete(entity) }
}
