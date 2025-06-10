package com.delhomme.jobbingtrack.interviews.viewmodels

import androidx.lifecycle.*
import com.delhomme.jobbingtrack.interviews.entities.InterviewTypeEntity
import com.delhomme.jobbingtrack.interviews.repositories.InterviewTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewTypeViewModel @Inject constructor(
    private val repo: InterviewTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: InterviewTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
