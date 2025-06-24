package com.delhomme.jobbingtrack.interviews.vms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
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
