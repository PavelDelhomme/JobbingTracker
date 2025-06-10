package com.delhomme.jobbingtrack.api.authentication.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.delhomme.jobbingtrack.api.authentication.repositories.RegisterRepository
import com.delhomme.jobbingtrack.api.authentication.services.RegisterService
import com.delhomme.jobbingtrack.api.tokens.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterService,
    private val tokenManager: TokenManager,
    @ApplicationContext context: Context
) : AndroidViewModel(context.applicationContext as Application) {
    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> = _registerSuccess

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = repository.register(email, password)
                if (result != null) {
                    tokenManager.saveTokens(result.access, result.refresh)
                    tokenManager.saveUser(result.user.id, result.access)
                    _registerSuccess.value = true
                } else {
                    _errorMessage.value = "Erreur lors de l'inscription"
                    _registerSuccess.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur réseau : ${e.message}"
                _registerSuccess.value = false
                Log.e("RegisterViewModel", "Error registering user", e)
            }
        }
    }
}
