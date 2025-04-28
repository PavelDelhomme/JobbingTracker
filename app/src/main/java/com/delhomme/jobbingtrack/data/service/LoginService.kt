package com.delhomme.jobbingtrack.data.service

import kotlinx.coroutines.delay

class LoginService {
    suspend fun login(email: String, password: String): Boolean {
        /*
        delay(2000) // Simulation 2 seconde pour faire "chargement..."

        // Simulation simple : succès si email contient "test" et password = "password"
        return email.contains("test") && password == "password"
        */
        // Simulation immédiate de succès pour développement
        return true
    }
}