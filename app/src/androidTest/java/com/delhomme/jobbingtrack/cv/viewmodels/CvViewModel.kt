package com.delhomme.jobbingtrack.cv.viewmodels


import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.repository.cv.CvRepository
import kotlinx.coroutines.launch

class CvViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = CvRepository(JobbingTrackApp.database.cvDao())

    val all: LiveData<List<CvEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<CvEntity?> = repo.byId(id).asLiveData()

    fun save(entity: CvEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: CvEntity) = viewModelScope.launch { repo.delete(entity) }
}
