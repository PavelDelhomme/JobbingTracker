package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.SkillEntity
import com.delhomme.jobbingtrack.data.local.repository.SkillRepository
import kotlinx.coroutines.launch

class SkillViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = SkillRepository(JobbingTrackApp.database.skillDao())

    val all: LiveData<List<SkillEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<SkillEntity?> = repo.byId(id).asLiveData()

    fun save(entity: SkillEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: SkillEntity) = viewModelScope.launch { repo.delete(entity) }
}
