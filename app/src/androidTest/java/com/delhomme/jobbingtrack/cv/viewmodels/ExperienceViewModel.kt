package com.delhomme.jobbingtrack.cv.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.cv.entities.ExperienceEntity
import com.delhomme.jobbingtrack.cv.repositories.ExperienceRepository
import kotlinx.coroutines.launch


class ExperienceViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ExperienceRepository(JobbingTrackApp.database.experienceDao())

    val all: LiveData<List<ExperienceEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<ExperienceEntity?> = repo.byId(id).asLiveData()

    fun save(entity: ExperienceEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: ExperienceEntity) = viewModelScope.launch { repo.delete(entity) }
}
