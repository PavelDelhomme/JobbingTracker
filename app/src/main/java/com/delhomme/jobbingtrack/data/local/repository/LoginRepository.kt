package com.delhomme.jobbingtrack.data.local.repository

import com.delhomme.jobbingtrack.data.models.LoginResponse
import com.delhomme.jobbingtrack.data.service.LoginService

class LoginRepository (
    private val loginService: LoginService = LoginService()
) {
    suspend fun login(email: String, password: String): LoginResponse? {
        return loginService.login(email, password)
    }
}