package com.delhomme.jobbingtrack.services.api

import javax.inject.Inject


class RegisterService @Inject constructor(private val api: ApiService) {
    suspend fun register(email: String, password: String): RegisterResponse? {
        val response = api.register(RegisterRequest(email, password))
        return if (response.isSuccessful) response.body() else null
    }
    /*fun fileToMultipart(file: File): MultipartBody.Part {
        val requestBody = file.asRequestBody("application/pdf".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", file.name, requestBody)
    }*/

}


class LoginService @Inject constructor(private val api: ApiService) {
    suspend fun login(email: String, password: String): LoginResponse? {
        val response = api.login(LoginRequest(email, password))
        return if (response.isSuccessful) response.body() else null
    }
}