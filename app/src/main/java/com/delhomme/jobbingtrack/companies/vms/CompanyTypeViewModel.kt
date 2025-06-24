package com.delhomme.jobbingtrack.companies.vms


@HiltViewModel
class CompanyTypeViewModel @Inject constructor(
    private val repo: CompanyTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: CompanyTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: CompanyTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
