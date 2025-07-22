package com.delhomme.jobbingtrack

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Observer les changements d'état
        viewModel.authState.observe(this) { state ->
            when (state) {
                is AuthState.Authenticated -> navigateToHome()
                is AuthState.Unauthenticated -> navigateToLogin()
                is AuthState.Loading -> {} // Afficher un indicateur de chargement si nécessaire
                is AuthState.Error -> handleError(state.message)
            }
        }

        // Vérifier l'état de l'authentification
        viewModel.checkAuthStatus()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun handleError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        navigateToLogin()
    }
}