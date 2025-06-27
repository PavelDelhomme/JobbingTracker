package com.delhomme.jobbingtrack.features.contact.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.features.contact.data.entities.DepartmentTypeEntity
import com.delhomme.jobbingtrack.features.contact.data.repositories.DepartmentTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DepartmentTypeViewModel @Inject constructor(
    private val repo: DepartmentTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: DepartmentTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: DepartmentTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
