package com.delhomme.jobbingtrack.data.viewmodel.Profil

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.cv.EducationEntity
import com.delhomme.jobbingtrack.data.local.repository.cv.EducationRepository
import kotlinx.coroutines.launch

class EducationViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EducationRepository(JobbingTrackApp.database.educationDao())

    val all: LiveData<List<EducationEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<EducationEntity?> = repo.byId(id).asLiveData()

    fun save(entity: EducationEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: EducationEntity) = viewModelScope.launch { repo.delete(entity) }
}
