package com.delhomme.jobbingtrack.ui.major.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import com.delhomme.jobbingtrack.data.repository.RegisterRepository
import com.delhomme.jobbingtrack.utils.TokenManager

class RegisterViewModel(
    app: Application,
    private val repository: RegisterRepository = RegisterRepository()
) : AndroidViewModel(app) {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _loginSuccess = MutableLiveData(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    /**
     * Simule un appel d'API d'inscription, sauvegarde un token factice
     * et notifie la vue.
     */
    fun register(email: String, password: String) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            val success = repository.register(email, password)
            _isLoading.value = false
            if (success) {
                // Simuler un token : dans la vraie vie il faudra prendre response.access
                val fakeToken = "fake_jwt_token_${System.currentTimeMillis()}"
                // Sauvegarde en SharedPreferences chiffrées
                TokenManager.saveToken(getApplication(), fakeToken)
                _loginSuccess.value = true
            } else {
                _errorMessage.value = "Email ou mot de passe incorrect."
            }
        }
    }
}
