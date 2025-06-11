package com.delhomme.jobbingtrack.api.authentication

import javax.inject.Inject


class RegisterRepository @Inject constructor(
    private val registerService: RegisterService
) {

    suspend fun register(email: String, password: String): RegisterResponse? {
        return registerService.register(email, password)
    }
}

class LoginRepository @Inject constructor(
    private val loginService: LoginService
) {
    suspend fun login(email: String, password: String): LoginResponse? {
        return loginService.login(email, password)
    }
}