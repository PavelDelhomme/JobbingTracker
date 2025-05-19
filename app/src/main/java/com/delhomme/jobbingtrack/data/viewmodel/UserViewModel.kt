package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.UserEntity
import com.delhomme.jobbingtrack.data.local.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = UserRepository(
        JobbingTrackApp.database.userDao()
    )

    val users = repo.users.asLiveData()

    fun save(user: UserEntity) = viewModelScope.launch {
        repo.save(user)
    }

    fun archive(id: String) = viewModelScope.launch {
        repo.archive(id)
    }

    fun delete(id: String) = viewModelScope.launch {
        repo.delete(id)
    }
}