package com.delhomme.jobbingtrack.api.authentication.repositories

import com.delhomme.jobbingtrack.api.authentication.responses.LoginResponse
import com.delhomme.jobbingtrack.api.authentication.services.LoginService


class LoginRepository (
    private val loginService: LoginService = LoginService()
) {
    suspend fun login(email: String, password: String): LoginResponse? {
        return loginService.login(email, password)
    }
}