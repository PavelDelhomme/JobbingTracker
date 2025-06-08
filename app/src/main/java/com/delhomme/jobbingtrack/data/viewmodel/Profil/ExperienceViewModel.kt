package com.delhomme.jobbingtrack.data.viewmodel.Profil

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.cv.ExperienceEntity
import com.delhomme.jobbingtrack.data.local.repository.cv.ExperienceRepository
import kotlinx.coroutines.launch

class ExperienceViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ExperienceRepository(JobbingTrackApp.database.experienceDao())

    val all: LiveData<List<ExperienceEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<ExperienceEntity?> = repo.byId(id).asLiveData()

    fun save(entity: ExperienceEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: ExperienceEntity) = viewModelScope.launch { repo.delete(entity) }
}
