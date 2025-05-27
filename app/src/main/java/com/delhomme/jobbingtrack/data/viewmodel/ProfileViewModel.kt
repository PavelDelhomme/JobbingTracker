package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.ProfileEntity
import com.delhomme.jobbingtrack.data.local.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ProfileRepository(
        JobbingTrackApp.database.profileDao()
    )

    val allProfiles: LiveData<List<ProfileEntity>> = repo.getAll().asLiveData()

    fun profileForUser(userId: String): LiveData<ProfileEntity?> =
        repo.byId(userId).asLiveData()

    fun save(profile: ProfileEntity) = viewModelScope.launch {
        repo.save(profile)
    }

    fun update(profile: ProfileEntity) = viewModelScope.launch {
        repo.update(profile)
    }

    fun archive(id: String) = viewModelScope.launch {
        val current = repo.byIdNow(id)
        current?.let {
            val updated = it.copy(
                isArchived = true,
                archivedAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            repo.update(updated)
        }
    }

    fun delete(id: String) = viewModelScope.launch {
        repo.delete(id)
    }
}
