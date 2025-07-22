package com.delhomme.jobbingtrack

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.core.network.tokens.TokenManager
import com.delhomme.jobbingtrack.features.authentication.data.repositories.AuthRepository
import com.delhomme.jobbingtrack.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isAuthenticated by viewModel.isAuthenticated.observeAsState(false)
    val isChecking by viewModel.isChecking.observeAsState(true)

    LaunchedEffect(isAuthenticated, isChecking) {
        if (!isChecking) {
            if (isAuthenticated) {
                navController.navigate(Routes.MAIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            } else {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo ou animation de chargement
            Text(
                text = "JobbingTrack",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isChecking) {
                CircularProgressIndicator()
            }
        }
    }
}
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun checkAuthStatus() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            if (tokenManager.isLoggedIn()) {
                try {
                    // Vérifier si le token est valide en appelant un endpoint protégé
                    val profileResponse = authRepository.getProfile()

                    if (profileResponse != null) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        // Si la réponse est null, c'est que le token n'est plus valide
                        tokenManager.clearTokens()
                        _authState.value = AuthState.Unauthenticated
                    }
                } catch (e: Exception) {
                    _authState.value = AuthState.Error(e.message ?: "Une erreur est survenue")
                }
            } else {
                _authState.value = AuthState.Unauthenticated
            }
        }
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}