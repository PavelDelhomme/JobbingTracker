package com.delhomme.jobbingtrack.applications.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.applications.ContractTypeEntity
import com.delhomme.jobbingtrack.applications.repo.ContractTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContractTypeViewModel @Inject constructor(
    private val repo: ContractTypeRepository
) : ViewModel() {
    val all: LiveData<List<ContractTypeEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ContractTypeEntity?> = repo.byId(id).asLiveData()
    fun save(entity: ContractTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: ContractTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}