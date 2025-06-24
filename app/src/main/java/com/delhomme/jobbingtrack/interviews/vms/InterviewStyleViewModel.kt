package com.delhomme.jobbingtrack.interviews.vms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InterviewStyleViewModel @Inject constructor(
    private val repo: InterviewStyleRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: InterviewStyleEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: InterviewStyleEntity) = viewModelScope.launch { repo.delete(entity) }
}

