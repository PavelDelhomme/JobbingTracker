package com.delhomme.jobbingtrack.features.interview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStatusEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStyleEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewTypeEntity
import com.delhomme.jobbingtrack.features.interview.data.repositories.InterviewStatusRepository
import com.delhomme.jobbingtrack.features.interview.data.repositories.InterviewStyleRepository
import com.delhomme.jobbingtrack.features.interview.data.repositories.InterviewTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InterviewStatusViewModel @Inject constructor(
    private val repo: InterviewStatusRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: InterviewStatusEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewStatusEntity) = viewModelScope.launch { repo.delete(entity) }
    fun archive(id: String) = viewModelScope.launch { repo.archive(id) }
    fun restore(id: String) = viewModelScope.launch { repo.restore(id) }
}


@HiltViewModel
class InterviewStyleViewModel @Inject constructor(
    private val repo: InterviewStyleRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: InterviewStyleEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewStyleEntity) = viewModelScope.launch { repo.delete(entity) }
}


@HiltViewModel
class InterviewTypeViewModel @Inject constructor(
    private val repo: InterviewTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: InterviewTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
