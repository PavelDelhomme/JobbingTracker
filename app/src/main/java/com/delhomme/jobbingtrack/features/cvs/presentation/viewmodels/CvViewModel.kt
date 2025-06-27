package com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.cvs.data.repositories.CvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CvViewModel @Inject constructor(
    private val repo: CvRepository
) : ViewModel() {
    val all: LiveData<List<CVEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<CVEntity?> = repo.byId(id).asLiveData()
    fun save(entity: CVEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: CVEntity) = viewModelScope.launch { repo.delete(entity) }
}