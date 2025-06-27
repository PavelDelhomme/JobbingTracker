package com.delhomme.jobbingtrack.features.authentication.presentation.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val tokenManager: TokenManager,
    @ApplicationContext context: Context,
) : AndroidViewModel(context.applicationContext as Application) {

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
                tokenManager.saveTokens(response.access, response.refresh)
                _loginSuccess.value = true
            } else {
                _errorMessage.value = "Email ou mot de passe incorrect."
            }
        }
    }
}