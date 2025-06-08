package com.delhomme.jobbingtrack.cv.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.cv.entities.EducationEntity
import com.delhomme.jobbingtrack.cv.repositories.EducationRepository
import kotlinx.coroutines.launch

class EducationViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = EducationRepository(JobbingTrackApp.database.educationDao())

    val all: LiveData<List<EducationEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<EducationEntity?> = repo.byId(id).asLiveData()

    fun save(entity: EducationEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: EducationEntity) = viewModelScope.launch { repo.delete(entity) }
}
