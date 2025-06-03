package com.delhomme.jobbingtrack.data.remote

import com.delhomme.jobbingtrack.data.classes.CV
import com.delhomme.jobbingtrack.data.models.LoginRequest
import com.delhomme.jobbingtrack.data.models.LoginResponse
import com.delhomme.jobbingtrack.data.models.RegisterRequest
import com.google.ai.client.generativeai.common.shared.Part
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST

data class RefreshTokenRequest(val refresh: String)
data class AccessTokenResponse(val access: String)


interface ApiService {
    @POST("auth/login/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    @POST("auth/token/refresh/")
    fun refreshToken(@Body body: RefreshTokenRequest): Call<AccessTokenResponse>
    @POST("auth/register/")
    suspend fun register(@Body body: RegisterRequest): Response<LoginResponse>
    @Multipart
    @POST("user/avatar/")
    suspend fun uploadAvatar(): Response<String>//TODO

    @Multipart
    @POST("api/profiles/upload-cv/")
    suspend fun uploadCV(@Part file: MultipartBody.Part): CV
}
