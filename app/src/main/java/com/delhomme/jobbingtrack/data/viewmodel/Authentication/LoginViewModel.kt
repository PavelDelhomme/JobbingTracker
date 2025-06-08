package com.delhomme.jobbingtrack.data.viewmodel.Authentication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import com.delhomme.jobbingtrack.data.local.repository.authentication.LoginRepository
import com.delhomme.jobbingtrack.data.api.tokens.TokenManager

class LoginViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = LoginRepository()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _loginSuccess = MutableLiveData(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    fun login(email: String, password: String) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            val response = repository.login(email, password)
            _isLoading.value = false
            if (response != null) {
                TokenManager.saveTokens(getApplication(), response.access, response.refresh)
                _loginSuccess.value = true
            } else {
                _errorMessage.value = "Email ou mot de passe incorrect."
            }
        }
    }
}
