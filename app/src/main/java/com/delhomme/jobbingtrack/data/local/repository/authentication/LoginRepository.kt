package com.delhomme.jobbingtrack.data.local.repository.authentication

import com.delhomme.jobbingtrack.data.models.responses.LoginResponse
import com.delhomme.jobbingtrack.data.services.authentication.LoginService

class LoginRepository (
    private val loginService: LoginService = LoginService()
) {
    suspend fun login(email: String, password: String): LoginResponse? {
        return loginService.login(email, password)
    }
}