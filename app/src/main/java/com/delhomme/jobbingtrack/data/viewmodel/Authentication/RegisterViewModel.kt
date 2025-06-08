package com.delhomme.jobbingtrack.data.viewmodel.Authentication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import com.delhomme.jobbingtrack.data.api.tokens.TokenManager
import com.delhomme.jobbingtrack.data.services.authentication.RegisterService

class RegisterViewModel(app: Application) : AndroidViewModel(app) {
    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> = _registerSuccess

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = RegisterService().register(email, password)
                if (result != null) {
                    TokenManager.saveTokens(getApplication(), result.access, result.refresh)
                    TokenManager.saveUser(getApplication(), result.user.id, result.access)
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
