package com.delhomme.jobbingtrack.cvs.viewmodels
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.cvs.entities.SkillEntity
import com.delhomme.jobbingtrack.cvs.repositories.SkillRepository
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
