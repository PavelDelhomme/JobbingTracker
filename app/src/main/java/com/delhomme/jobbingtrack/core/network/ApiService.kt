package com.delhomme.jobbingtrack.core.network

import android.provider.ContactsContract.Profile
import com.delhomme.jobbingtrack.features.authentication.domain.model.LoginRequest
import com.delhomme.jobbingtrack.features.authentication.domain.model.LoginResponse
import com.delhomme.jobbingtrack.features.authentication.domain.model.RegisterRequest
import com.delhomme.jobbingtrack.features.authentication.domain.model.RegisterResponse
import com.delhomme.jobbingtrack.features.user.domain.models.UserInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST


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
}