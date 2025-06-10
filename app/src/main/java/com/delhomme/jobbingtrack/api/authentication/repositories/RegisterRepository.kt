package com.delhomme.jobbingtrack.api.authentication.repositories

import com.delhomme.jobbingtrack.api.ApiService
import com.delhomme.jobbingtrack.api.authentication.responses.RegisterResponse
import com.delhomme.jobbingtrack.api.authentication.services.RegisterService
import javax.inject.Inject


class RegisterRepository @Inject constructor(
    private val registerService: RegisterService
) {

    suspend fun register(email: String, password: String): RegisterResponse? {
        return registerService.register(email, password)
    }
}
