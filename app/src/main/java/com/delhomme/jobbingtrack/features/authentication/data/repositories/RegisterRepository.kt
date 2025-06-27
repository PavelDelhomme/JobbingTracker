package com.delhomme.jobbingtrack.features.authentication.data.repositories

import javax.inject.Inject


class RegisterRepository @Inject constructor(
    private val registerService: RegisterService
) {

    suspend fun register(email: String, password: String): RegisterResponse? {
        return registerService.register(email, password)
    }
}