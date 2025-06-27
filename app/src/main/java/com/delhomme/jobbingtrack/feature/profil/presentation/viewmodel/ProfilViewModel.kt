package com.delhomme.jobbingtrack.feature.profil.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.api.tokens.TokenManager
import kotlinx.coroutines.launch


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
