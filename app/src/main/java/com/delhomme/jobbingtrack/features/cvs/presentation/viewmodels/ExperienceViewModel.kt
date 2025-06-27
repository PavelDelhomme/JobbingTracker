package com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.features.cvs.data.entities.ExperienceEntity
import com.delhomme.jobbingtrack.features.cvs.data.repositories.ExperienceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExperienceViewModel @Inject constructor(
    private val repo: ExperienceRepository
) : ViewModel() {
    val all: LiveData<List<ExperienceEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ExperienceEntity?> = repo.byId(id).asLiveData()
    fun save(entity: ExperienceEntity) = viewModelScope.launch { repo.save(entity) }
    fun update(entity: ExperienceEntity) = viewModelScope.launch { repo.update(entity) }
    fun delete(entity: ExperienceEntity) = viewModelScope.launch { repo.delete(entity) }
}