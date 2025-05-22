package com.delhomme.jobbingtrack.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.data.local.entities.UserEntity
import com.delhomme.jobbingtrack.data.local.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = UserRepository(JobbingTrackApp.database.userDao())

    val users: LiveData<List<UserEntity>> = repo.all.asLiveData()

    /** Expose un seul user */
    fun userById(id: String): LiveData<UserEntity?> =
        repo.byId(id).asLiveData()

    fun save(user: UserEntity) = viewModelScope.launch {
        repo.save(user)
    }
    fun update(user: UserEntity) = viewModelScope.launch {
        repo.update(user)
    }
    fun archive(id: String) = viewModelScope.launch {
        repo.archive(id)
    }
    fun delete(id: String) = viewModelScope.launch {
        repo.delete(id)
    }
    fun deleteAll() = viewModelScope.launch {
        repo.deleteAll()
    }
}