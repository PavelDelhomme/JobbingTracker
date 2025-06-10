package com.delhomme.jobbingtrack.cvs.viewmodels
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.cvs.entities.CVEntity
import com.delhomme.jobbingtrack.cvs.repositories.CvRepository
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
