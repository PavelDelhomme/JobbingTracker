package com.delhomme.jobbingtrack.api.authentication.repositories

import com.delhomme.jobbingtrack.api.authentication.responses.LoginResponse
import com.delhomme.jobbingtrack.api.authentication.services.LoginService
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val loginService: LoginService
) {
    suspend fun login(email: String, password: String): LoginResponse? {
        return loginService.login(email, password)
    }
}