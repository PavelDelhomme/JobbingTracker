package com.delhomme.jobbingtrack.data.remote

import com.delhomme.jobbingtrack.data.models.LoginRequest
import com.delhomme.jobbingtrack.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
