package com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels


@HiltViewModel
class EducationViewModel @Inject constructor(
    private val repo: EducationRepository
) : ViewModel() {
    val all: LiveData<List<EducationEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<EducationEntity?> = repo.byId(id).asLiveData()
    fun save(entity: EducationEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: EducationEntity) = viewModelScope.launch { repo.delete(entity) }
}