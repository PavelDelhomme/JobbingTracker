package com.delhomme.jobbingtrack.datas.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.api.tokens.TokenManager
import com.delhomme.jobbingtrack.datas.entities.profiles.ProfilEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: ProfileViewModel,
    @ApplicationContext context: Context
) : ViewModel() {
    val profileLive: LiveData<ProfilEntity?> = profileForUser(TokenManager.getUserId(context) ?: "")


    fun profileForUser(userId: String): LiveData<ProfilEntity?> =
        repo.byId(userId).asLiveData()

    fun save(profile: ProfilEntity) = viewModelScope.launch {
        repo.save(profile)
    }

    fun update(profile: ProfilEntity) = viewModelScope.launch {
        repo.update(profile)
    }

    fun delete(id: String) = viewModelScope.launch {
        repo.delete(id)
    }
}
