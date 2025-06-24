package com.delhomme.jobbingtrack.cvs.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val repo: ProjectRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val all: LiveData<List<ProjectEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ProjectEntity?> = repo.byId(id).asLiveData()
    fun save(entity: ProjectEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: ProjectEntity) = viewModelScope.launch { repo.delete(entity) }
}

