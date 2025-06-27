package com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.features.cvs.data.entities.EducationEntity
import com.delhomme.jobbingtrack.features.cvs.data.repositories.EducationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EducationViewModel @Inject constructor(
    private val repo: EducationRepository
) : ViewModel() {
    val all: LiveData<List<EducationEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<EducationEntity?> = repo.byId(id).asLiveData()
    fun save(entity: EducationEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: EducationEntity) = viewModelScope.launch { repo.delete(entity) }
}