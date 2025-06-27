package com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels


@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val repo: ProjectRepository
) : ViewModel() {
    val all: LiveData<List<ProjectEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ProjectEntity?> = repo.byId(id).asLiveData()
    fun save(entity: ProjectEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: ProjectEntity) = viewModelScope.launch { repo.delete(entity) }
}