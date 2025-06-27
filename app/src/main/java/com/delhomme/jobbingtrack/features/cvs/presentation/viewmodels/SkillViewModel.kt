package com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.features.cvs.data.entities.SkillEntity
import com.delhomme.jobbingtrack.features.cvs.data.repositories.SkillRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SkillViewModel @Inject constructor(
    private val repo: SkillRepository
) : ViewModel() {
    val all: LiveData<List<SkillEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<SkillEntity?> = repo.byId(id).asLiveData()
    fun save(entity: SkillEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: SkillEntity) = viewModelScope.launch { repo.delete(entity) }
}