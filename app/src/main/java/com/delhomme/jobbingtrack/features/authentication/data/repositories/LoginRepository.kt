package com.delhomme.jobbingtrack.features.authentication.data.repositories

import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val loginService: LoginService
) {
    suspend fun login(email: String, password: String): LoginResponse? {
        return loginService.login(email, password)
    }
}