package com.delhomme.jobbingtrack.data.services.authentication

import com.delhomme.jobbingtrack.data.models.requests.LoginRequest
import com.delhomme.jobbingtrack.data.models.responses.LoginResponse
import com.delhomme.jobbingtrack.data.api.ApiClient.api

class LoginService {
    suspend fun login(email: String, password: String): LoginResponse? {
        val response = api.login(LoginRequest(email, password))
        return if (response.isSuccessful) response.body() else null
    }
}
