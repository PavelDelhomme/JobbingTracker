package com.delhomme.jobbingtrack.profiles.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.cv.ProfilEntity
import com.delhomme.jobbingtrack.data.local.repository.cv.ProfilRepository
import com.delhomme.jobbingtrack.data.api.tokens.TokenManager
import kotlinx.coroutines.launch

class ProfileViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ProfilRepository(
        JobbingTrackApp.database.profileDao()
    )

    val profileLive: LiveData<ProfilEntity?> = profileForUser(TokenManager.getUserId(app.applicationContext) ?: "")


    fun profileForUser(userId: String): LiveData<ProfilEntity?> =
        repo.byId(userId).asLiveData()

    fun save(profile: ProfilEntity) = viewModelScope.launch {
        repo.save(profile)
    }

    fun update(profile: ProfilEntity) = viewModelScope.launch {
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
