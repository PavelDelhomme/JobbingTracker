package com.delhomme.jobbingtrack.features.contact.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PositionTypeViewModel @Inject constructor(
    private val repo: PositionTypeRepository
) : ViewModel() {
    val all = repo.all.asLiveData()
    fun byId(id: String) = repo.byId(id).asLiveData()
    fun save(entity: PositionTypeEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: PositionTypeEntity) = viewModelScope.launch { repo.delete(entity) }
}
