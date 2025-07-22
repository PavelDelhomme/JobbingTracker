package com.delhomme.jobbingtrack.features.authentication.data.repositories

import com.delhomme.jobbingtrack.core.network.ApiService
import com.delhomme.jobbingtrack.features.authentication.domain.model.LoginRequest
import com.delhomme.jobbingtrack.features.authentication.domain.model.LoginResponse
import com.delhomme.jobbingtrack.features.authentication.domain.model.RegisterRequest
import com.delhomme.jobbingtrack.features.authentication.domain.model.RegisterResponse
import com.delhomme.jobbingtrack.features.profil.domain.model.ProfileResponse
import com.delhomme.jobbingtrack.features.user.domain.models.UserInfo
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(email: String, password: String): LoginResponse? {
        val response = apiService.login(LoginRequest(email, password))
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun register(email: String, password: String, firstName: String = "", lastName: String = ""): RegisterResponse? {
        val response = apiService.register(RegisterRequest(
            email = email,
            password = password,
            password2 = password,
            first_name = firstName,
            last_name = lastName
        ))
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getProfile(): ProfileResponse? {
        val response = apiService.getMyProfile()
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getCurrentUser(): UserInfo? {
        val response = apiService.getCurrentUser()
        return if (response.isSuccessful) response.body() else null
    }
}