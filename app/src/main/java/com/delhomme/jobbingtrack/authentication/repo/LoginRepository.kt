package com.delhomme.jobbingtrack.authentication.repo

import com.delhomme.jobbingtrack.authentication.responses.LoginResponse
import com.delhomme.jobbingtrack.services.LoginService
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val loginService: LoginService
) {
    suspend fun login(email: String, password: String): LoginResponse? {
        return loginService.login(email, password)
    }
}