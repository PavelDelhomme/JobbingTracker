package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.repository.LanguageRepository
import kotlinx.coroutines.launch

class LanguageViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = LanguageRepository(JobbingTrackApp.database.interviewDao())

    val all: LiveData<List<LanguageEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<LanguageEntity?> = repo.byId(id).asLiveData()

    fun save(entity: LanguageEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: LanguageEntity) = viewModelScope.launch { repo.delete(entity) }
}
