package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.service.RegisterService
import com.delhomme.jobbingtrack.data.models.RegisterResponse

class RegisterRepository(
    private val registerService: RegisterService = RegisterService()
) {
    suspend fun register(email: String, password: String): RegisterResponse? {
        return registerService.register(email, password)
    }
}
