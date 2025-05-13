package com.delhomme.jobbingtrack.ui.major.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import com.delhomme.jobbingtrack.data.repository.LoginRepository

class LoginViewModel(
    private val repository: LoginRepository = LoginRepository()
) : ViewModel() {

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
            val success = repository.login(email, password)
            _isLoading.value = false
            if (success) {
                _loginSuccess.value = true
            } else {
                _errorMessage.value = "Email ou mot de passe incorrect."
            }
        }
    }
}
