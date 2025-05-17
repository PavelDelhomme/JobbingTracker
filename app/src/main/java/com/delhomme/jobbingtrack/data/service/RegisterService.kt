package com.delhomme.jobbingtrack.data.service

import kotlinx.coroutines.delay

class RegisterService {
    suspend fun register(email: String, password: String): Boolean {
        // Simuler un temps de réponse réseaux
        delay(1000)
        // Toujours ok pour dev
        return true
    }
}