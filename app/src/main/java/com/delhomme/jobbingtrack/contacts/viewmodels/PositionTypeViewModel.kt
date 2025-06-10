package com.delhomme.jobbingtrack.contacts.viewmodels

import androidx.lifecycle.*
import com.delhomme.jobbingtrack.contacts.entities.PositionTypeEntity
import com.delhomme.jobbingtrack.contacts.repositories.PositionTypeRepository
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
