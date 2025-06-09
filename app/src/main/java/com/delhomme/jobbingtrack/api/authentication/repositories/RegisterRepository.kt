package com.delhomme.jobbingtrack.api.authentication.repositories

import com.delhomme.jobbingtrack.api.authentication.responses.RegisterResponse
import com.delhomme.jobbingtrack.api.authentication.services.RegisterService


class RegisterRepository(
    private val registerService: RegisterService = RegisterService()
) {
    suspend fun register(email: String, password: String): RegisterResponse? {
        return registerService.register(email, password)
    }
}
