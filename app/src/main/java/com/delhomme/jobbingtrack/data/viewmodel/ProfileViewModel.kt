package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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


    val profiles = repo.profiles.asLiveData()

    fun save(profile: ProfileEntity) = viewModelScope.launch {
        repo.save(profile)
    }

    fun archive(id: String) = viewModelScope.launch {
        repo.archive(id)
    }

    fun delete(id: String) = viewModelScope.launch {
        repo.delete(id)
    }
}