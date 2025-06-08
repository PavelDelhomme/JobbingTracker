package com.delhomme.jobbingtrack.api.authentication.services

import com.delhomme.jobbingtrack.api.ApiClient.api
import com.delhomme.jobbingtrack.api.authentication.requests.RegisterRequest
import com.delhomme.jobbingtrack.api.authentication.responses.RegisterResponse


class RegisterService {
    suspend fun register(email: String, password: String): RegisterResponse? {
        val response = api.register(RegisterRequest(email, password))
        return if (response.isSuccessful) response.body() else null
    }
    /*fun fileToMultipart(file: File): MultipartBody.Part {
        val requestBody = file.asRequestBody("application/pdf".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", file.name, requestBody)
    }*/

}
