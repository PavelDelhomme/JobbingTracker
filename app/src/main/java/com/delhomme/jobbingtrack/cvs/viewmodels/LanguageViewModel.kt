package com.delhomme.jobbingtrack.cvs.viewmodels
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.cvs.entities.LanguageEntity
import com.delhomme.jobbingtrack.cvs.repositories.LanguageRepository
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
