package com.delhomme.jobbingtrack.data.service

import com.delhomme.jobbingtrack.data.models.LoginResponse
import com.delhomme.jobbingtrack.data.models.RegisterRequest
import com.delhomme.jobbingtrack.data.models.RegisterResponse
import com.delhomme.jobbingtrack.data.remote.ApiClient.api
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

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
