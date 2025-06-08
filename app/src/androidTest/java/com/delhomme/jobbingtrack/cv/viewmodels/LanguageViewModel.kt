package com.delhomme.jobbingtrack.cv.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.cv.entities.LanguageEntity
import com.delhomme.jobbingtrack.cv.repositories.LanguageRepository
import kotlinx.coroutines.launch


class LanguageViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = LanguageRepository(JobbingTrackApp.database.interviewDao())

    val all: LiveData<List<LanguageEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<LanguageEntity?> = repo.byId(id).asLiveData()

    fun save(entity: LanguageEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: LanguageEntity) = viewModelScope.launch { repo.delete(entity) }
}
