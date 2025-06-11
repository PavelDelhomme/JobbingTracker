package com.delhomme.jobbingtrack.api

import com.delhomme.jobbingtrack.api.authentication.LoginRequest
import com.delhomme.jobbingtrack.api.authentication.RegisterRequest
import com.delhomme.jobbingtrack.api.authentication.LoginResponse
import com.delhomme.jobbingtrack.api.authentication.RegisterResponse
import com.delhomme.jobbingtrack.api.tokens.responses.RefreshToAccessTokenResponse
import com.delhomme.jobbingtrack.profiles.Profile
import com.delhomme.jobbingtrack.users.UserInfo


import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


data class RefreshTokenRequest(val refresh: String)


interface ApiService {
    @POST("auth/login/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    @POST("auth/refresh/")
    fun refreshToken(@Body body: RefreshTokenRequest): Call<RefreshToAccessTokenResponse>
    @POST("auth/register/")
    suspend fun register(@Body body: RegisterRequest): Response<RegisterResponse>
    @Multipart
    @POST("user/avatar/")
    suspend fun uploadAvatar(): Response<String>//TODO


    @GET("auth/me/")
    suspend fun getCurrentUser(): Response<UserInfo>
    // Ajouter plus tard : GET/PUT sur `/profiles/` si tu veux éditer
    @GET("profiles/")
    suspend fun getMyProfile(): Profile
    @GET("profiles/{id}/")
    suspend fun getProfile(id: String): Profile
    //@POST("api/profiles/")
    //suspend fun createProfile(@Body profile: Profile): Profile
    @POST("profiles/{id}/")
    suspend fun updateProfile(@Body profile: Profile): Profile

    @Multipart
    @POST("profiles/upload-cv/")
    suspend fun uploadCV(@Part file: MultipartBody.Part): CV
}