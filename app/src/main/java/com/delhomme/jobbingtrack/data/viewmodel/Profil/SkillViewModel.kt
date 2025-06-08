package com.delhomme.jobbingtrack.data.viewmodel.Profil

import android.app.Application
import androidx.lifecycle.*
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.cv.SkillEntity
import com.delhomme.jobbingtrack.data.local.repository.cv.SkillRepository
import kotlinx.coroutines.launch

class SkillViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = SkillRepository(JobbingTrackApp.database.skillDao())

    val all: LiveData<List<SkillEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<SkillEntity?> = repo.byId(id).asLiveData()

    fun save(entity: SkillEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: SkillEntity) = viewModelScope.launch { repo.delete(entity) }
}
