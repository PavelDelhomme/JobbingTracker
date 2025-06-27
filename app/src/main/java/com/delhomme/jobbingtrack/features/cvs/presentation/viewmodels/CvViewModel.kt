package com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels


@HiltViewModel
class CvViewModel @Inject constructor(
    private val repo: CvRepository
) : ViewModel() {
    val all: LiveData<List<CVEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<CVEntity?> = repo.byId(id).asLiveData()
    fun save(entity: CVEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: CVEntity) = viewModelScope.launch { repo.delete(entity) }
}