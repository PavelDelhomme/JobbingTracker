package com.delhomme.jobbingtrack.companies.vms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.delhomme.jobbingtrack.companies.repo.CompanyTypeRepository
import com.delhomme.jobbingtrack.companies.CompanyTypeEntity
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompanyTypeViewModel @Inject constructor(
    private val repo: CompanyTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: CompanyTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: CompanyTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
