package com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels


@HiltViewModel
class SkillViewModel @Inject constructor(
    private val repo: SkillRepository
) : ViewModel() {
    val all: LiveData<List<SkillEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<SkillEntity?> = repo.byId(id).asLiveData()
    fun save(entity: SkillEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: SkillEntity) = viewModelScope.launch { repo.delete(entity) }
}