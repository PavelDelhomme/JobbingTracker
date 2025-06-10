package com.delhomme.jobbingtrack.cvs.viewmodels


import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.cvs.entities.CVEntity
import com.delhomme.jobbingtrack.cvs.repositories.CvRepository
import com.delhomme.jobbingtrack.data.local.repository.cv.CvRepository
import kotlinx.coroutines.launch

class CvViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = CvRepository(JobbingTrackApp.database.cvDao())

    val all: LiveData<List<CVEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<CVEntity?> = repo.byId(id).asLiveData()

    fun save(entity: CVEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: CVEntity) = viewModelScope.launch { repo.delete(entity) }
}
