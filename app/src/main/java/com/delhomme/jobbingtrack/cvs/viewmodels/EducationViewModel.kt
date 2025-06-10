package com.delhomme.jobbingtrack.cvs.viewmodels
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.cvs.entities.EducationEntity
import com.delhomme.jobbingtrack.cvs.repositories.EducationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EducationViewModel @Inject constructor(
    private val repo: EducationRepository
) : ViewModel() {
    val all: LiveData<List<EducationEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<EducationEntity?> = repo.byId(id).asLiveData()
    fun save(entity: EducationEntity) = viewModelScope.launch { repo.save(entity) }
    fun delete(entity: EducationEntity) = viewModelScope.launch { repo.delete(entity) }
}
