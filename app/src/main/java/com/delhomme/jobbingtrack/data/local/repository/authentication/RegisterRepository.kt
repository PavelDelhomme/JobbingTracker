package com.delhomme.jobbingtrack.data.local.repository.authentication

import com.delhomme.jobbingtrack.data.services.authentication.RegisterService
import com.delhomme.jobbingtrack.data.models.responses.RegisterResponse

class RegisterRepository(
    private val registerService: RegisterService = RegisterService()
) {
    suspend fun register(email: String, password: String): RegisterResponse? {
        return registerService.register(email, password)
    }
}
