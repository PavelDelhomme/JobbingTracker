package com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.features.cvs.data.entities.LanguageEntity
import com.delhomme.jobbingtrack.features.cvs.data.repositories.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val repo: LanguageRepository
) : ViewModel() {
    val all: LiveData<List<LanguageEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<LanguageEntity?> = repo.byId(id).asLiveData()
    fun save(entity: LanguageEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: LanguageEntity) = viewModelScope.launch { repo.delete(entity) }
}