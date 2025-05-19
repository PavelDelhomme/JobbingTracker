package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.service.RegisterService

class RegisterRepository(
    private val service: RegisterService = RegisterService()
) {
    suspend fun register(email: String, password: String): Boolean {
        // simule toujours le succès
        return service.register(email, password)
    }
}