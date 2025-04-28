package com.delhomme.jobbingtrack.data.repository

import com.delhomme.jobbingtrack.data.service.LoginService

class LoginRepository (
    private val loginService: LoginService = LoginService()
) {
    suspend fun login(email: String, password: String): Boolean {
        return loginService.login(email, password)
    }
}