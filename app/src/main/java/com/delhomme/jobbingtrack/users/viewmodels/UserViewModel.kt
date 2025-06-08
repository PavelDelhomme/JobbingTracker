package com.delhomme.jobbingtrack.users.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.JobbingTrackApp
import kotlinx.coroutines.launch


class UserViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = UserRepository(JobbingTrackApp.Companion.database.userDao())

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