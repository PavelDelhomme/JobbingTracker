package com.delhomme.jobbingtrack.cvs.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val repo: LanguageRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val all: LiveData<List<LanguageEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<LanguageEntity?> = repo.byId(id).asLiveData()
    fun save(entity: LanguageEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: LanguageEntity) = viewModelScope.launch { repo.delete(entity) }
}
