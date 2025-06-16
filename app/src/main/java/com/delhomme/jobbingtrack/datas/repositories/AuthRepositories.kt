package com.delhomme.jobbingtrack.datas.repositories

import com.delhomme.jobbingtrack.datas.responses.LoginResponse
import com.delhomme.jobbingtrack.datas.responses.RegisterResponse
import com.delhomme.jobbingtrack.services.LoginService
import com.delhomme.jobbingtrack.services.RegisterService
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