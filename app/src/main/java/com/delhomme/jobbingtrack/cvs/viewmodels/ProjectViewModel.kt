package com.delhomme.jobbingtrack.cvs.viewmodels
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.cvs.entities.ProjectEntity
import com.delhomme.jobbingtrack.cvs.repositories.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val repo: ProjectRepository
) : ViewModel() {
    val all: LiveData<List<ProjectEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ProjectEntity?> = repo.byId(id).asLiveData()
    fun save(entity: ProjectEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: ProjectEntity) = viewModelScope.launch { repo.delete(entity) }
}
