package com.delhomme.jobbingtrack.datas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.datas.entities.cvs.CVEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.CollaboratorEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.EducationEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.ExperienceEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.LanguageEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.ProjectEntity
import com.delhomme.jobbingtrack.datas.entities.cvs.SkillEntity
import com.delhomme.jobbingtrack.datas.repositories.CollaboratorRepository
import com.delhomme.jobbingtrack.datas.repositories.CvRepository
import com.delhomme.jobbingtrack.datas.repositories.EducationRepository
import com.delhomme.jobbingtrack.datas.repositories.ExperienceRepository
import com.delhomme.jobbingtrack.datas.repositories.LanguageRepository
import com.delhomme.jobbingtrack.datas.repositories.ProjectRepository
import com.delhomme.jobbingtrack.datas.repositories.SkillRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CvViewModel @Inject constructor(
    private val repo: CvRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val all: LiveData<List<CVEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<CVEntity?> = repo.byId(id).asLiveData()
    fun save(entity: CVEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: CVEntity) = viewModelScope.launch { repo.delete(entity) }
}


@HiltViewModel
class CollaboratorViewModel @Inject constructor(
    private val repo: CollaboratorRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val all: LiveData<List<CollaboratorEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<CollaboratorEntity?> = repo.byId(id).asLiveData()
    fun save(entity: CollaboratorEntity) = viewModelScope.launch { repo.save(entity) }
    fun update(entity: CollaboratorEntity) = viewModelScope.launch { repo.update(entity) }
    fun delete(entity: CollaboratorEntity) = viewModelScope.launch { repo.delete(entity) }
}


@HiltViewModel
class EducationViewModel @Inject constructor(
    private val repo: EducationRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val all: LiveData<List<EducationEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<EducationEntity?> = repo.byId(id).asLiveData()
    fun save(entity: EducationEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: EducationEntity) = viewModelScope.launch { repo.delete(entity) }
}



@HiltViewModel
class ExperienceViewModel @Inject constructor(
    private val repo: ExperienceRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val all: LiveData<List<ExperienceEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ExperienceEntity?> = repo.byId(id).asLiveData()
    fun save(entity: ExperienceEntity) = viewModelScope.launch { repo.save(entity) }
    fun update(entity: ExperienceEntity) = viewModelScope.launch { repo.update(entity) }
    fun delete(entity: ExperienceEntity) = viewModelScope.launch { repo.delete(entity) }
}



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



@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val repo: ProjectRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val all: LiveData<List<ProjectEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<ProjectEntity?> = repo.byId(id).asLiveData()
    fun save(entity: ProjectEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: ProjectEntity) = viewModelScope.launch { repo.delete(entity) }
}



@HiltViewModel
class SkillViewModel @Inject constructor(
    private val repo: SkillRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val all: LiveData<List<SkillEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<SkillEntity?> = repo.byId(id).asLiveData()
    fun save(entity: SkillEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: SkillEntity) = viewModelScope.launch { repo.delete(entity) }
}
