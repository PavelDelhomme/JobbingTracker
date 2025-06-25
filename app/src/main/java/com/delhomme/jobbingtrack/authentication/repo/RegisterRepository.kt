package com.delhomme.jobbingtrack.authentication.repo

import com.delhomme.jobbingtrack.authentication.responses.RegisterResponse
import com.delhomme.jobbingtrack.services.RegisterService
import javax.inject.Inject


class RegisterRepository @Inject constructor(
    private val registerService: RegisterService
) {

    suspend fun register(email: String, password: String): RegisterResponse? {
        return registerService.register(email, password)
    }
}