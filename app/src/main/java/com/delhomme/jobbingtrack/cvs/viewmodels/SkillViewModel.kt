package com.delhomme.jobbingtrack.cvs.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.cvs.entities.SkillEntity
import com.delhomme.jobbingtrack.cvs.repositories.SkillRepository
import kotlinx.coroutines.launch


class SkillViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = SkillRepository(JobbingTrackApp.database.skillDao())

    val all: LiveData<List<SkillEntity>> = repo.all.asLiveData()

    fun byId(id: String): LiveData<SkillEntity?> = repo.byId(id).asLiveData()

    fun save(entity: SkillEntity) = viewModelScope.launch { repo.save(entity) }

    fun delete(entity: SkillEntity) = viewModelScope.launch { repo.delete(entity) }
}
