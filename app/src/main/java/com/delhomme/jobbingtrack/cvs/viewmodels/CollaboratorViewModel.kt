package com.delhomme.jobbingtrack.cvs.viewmodels
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.cvs.entities.CollaboratorEntity
import com.delhomme.jobbingtrack.cvs.repositories.CollaboratorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollaboratorViewModel @Inject constructor(
    private val repo: CollaboratorRepository
) : ViewModel() {
    val all: LiveData<List<CollaboratorEntity>> = repo.all.asLiveData()
    fun byId(id: String): LiveData<CollaboratorEntity?> = repo.byId(id).asLiveData()
    fun save(entity: CollaboratorEntity) = viewModelScope.launch { repo.save(entity) }
    fun update(entity: CollaboratorEntity) = viewModelScope.launch { repo.update(entity) }
    fun delete(entity: CollaboratorEntity) = viewModelScope.launch { repo.delete(entity) }
}
