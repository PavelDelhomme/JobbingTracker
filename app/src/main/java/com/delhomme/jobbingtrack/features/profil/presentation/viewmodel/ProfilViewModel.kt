package com.delhomme.jobbingtrack.features.profil.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.core.network.tokens.TokenManager
import com.delhomme.jobbingtrack.features.profil.data.entities.ProfilEntity
import com.delhomme.jobbingtrack.features.profil.data.repositories.ProfilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfilViewModel @Inject constructor(
    private val repo: ProfilRepository,
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
