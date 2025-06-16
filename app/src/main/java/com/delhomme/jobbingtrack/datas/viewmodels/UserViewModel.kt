package com.delhomme.jobbingtrack.datas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.datas.entities.users.UserEntity
import com.delhomme.jobbingtrack.datas.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {
    val users: LiveData<List<UserEntity>> = repo.all.asLiveData()
    fun userById(id: String): LiveData<UserEntity?> = repo.byId(id).asLiveData()
    fun save(user: UserEntity) = viewModelScope.launch { repo.save(user) }
    fun update(user: UserEntity) = viewModelScope.launch { repo.update(user) }
    fun archive(id: String) = viewModelScope.launch { repo.archive(id) }
    fun delete(id: String) = viewModelScope.launch { repo.delete(id) }
    fun deleteAll() = viewModelScope.launch { repo.deleteAll() }
}
