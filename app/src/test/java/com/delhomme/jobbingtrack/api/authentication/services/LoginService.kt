package com.delhomme.jobbingtrack.api.authentication.services

import com.delhomme.jobbingtrack.api.ApiClient.api
import com.delhomme.jobbingtrack.api.authentication.requests.LoginRequest
import com.delhomme.jobbingtrack.api.authentication.responses.LoginResponse


class LoginService {
    suspend fun login(email: String, password: String): LoginResponse? {
        val response = api.login(LoginRequest(email, password))
        return if (response.isSuccessful) response.body() else null
    }
}
