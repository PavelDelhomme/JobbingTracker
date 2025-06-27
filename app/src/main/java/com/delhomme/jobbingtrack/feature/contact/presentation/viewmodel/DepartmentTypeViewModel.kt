package com.delhomme.jobbingtrack.feature.contact.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
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
