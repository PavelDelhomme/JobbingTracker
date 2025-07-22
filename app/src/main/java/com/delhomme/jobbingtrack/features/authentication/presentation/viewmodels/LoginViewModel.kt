package com.delhomme.jobbingtrack.features.authentication.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.network.tokens.TokenManager
import com.delhomme.jobbingtrack.features.authentication.data.repositories.LoginRepository
import com.delhomme.jobbingtrack.features.profil.data.entities.ProfilEntity
import com.delhomme.jobbingtrack.features.profil.data.repositories.ProfilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val tokenManager: TokenManager,
    private val profileRepository: ProfilRepository,
    @ApplicationContext context: Context,
) : AndroidViewModel(context.applicationContext as Application) {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _loginSuccess = MutableLiveData(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    init {
        // Vérifier si l'utilisateur est déjà connecté
        if (tokenManager.isLoggedIn()) {
            _loginSuccess.value = true
        }
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            val response = repository.login(email, password)
            _isLoading.value = false
            if (response != null) {
                tokenManager.saveTokens(response.access, response.refresh)
                tokenManager.saveUser(response.user.id, response.access)

                // Récupérer le profil utilisateur
                fetchUserProfile()

                _loginSuccess.value = true
            } else {
                _errorMessage.value = "Email ou mot de passe incorrect."
            }
        }
    }

    private suspend fun fetchUserProfile() {
        try {
            val profileResponse = repository.getProfile()
            if (profileResponse != null) {
                // Sauvegarder le profil en local
                val userId  = tokenManager.getUserId() ?: return

                // Convertir le ProfileResponse en ProfilEntity
                val profileEntity = ProfilEntity(
                    id = profileResponse.id,
                    userId = userId,
                    subject = "", // Champ à adapter
                    companyIds = "", // Champ à adapter
                    contactIds = null,
                    applicationIds = null,
                    followUpIds = null,
                    notes = null,
                    base = CommonEntityFields(
                        createdAt = System.currentTimeMillis(),
                        updatedAt = System.currentTimeMillis(),
                        isArchived = false,
                        isDeleted = false,
                        syncHash = "",
                        archivedAt = null,
                        deletedAt = null,
                        userId = userId,
                    )
                )

                profileRepository.save(profileEntity)
            }
        } catch (e: Exception) {
            // Gérer l'erreur silencieusement, l'utilisateur peut toujours continuer
            Log.e("LoginViewModel", "Error fetching profile", e)
        }
    }

    fun logout() {
        tokenManager.clearTokens()
        _loginSuccess.value = false
    }
}