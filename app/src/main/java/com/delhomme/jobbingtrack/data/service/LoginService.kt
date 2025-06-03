package com.delhomme.jobbingtrack.data.service

import com.delhomme.jobbingtrack.data.models.LoginRequest
import com.delhomme.jobbingtrack.data.models.LoginResponse
import com.delhomme.jobbingtrack.data.remote.ApiClient.api

class LoginService {
    suspend fun login(email: String, password: String): LoginResponse? {
        val response = api.login(LoginRequest(email, password))
        return if (response.isSuccessful) response.body() else null
    }
}
