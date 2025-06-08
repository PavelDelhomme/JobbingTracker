package com.delhomme.jobbingtrack.data.services.authentication

import com.delhomme.jobbingtrack.data.models.requests.RegisterRequest
import com.delhomme.jobbingtrack.data.models.responses.RegisterResponse
import com.delhomme.jobbingtrack.data.api.ApiClient.api

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
