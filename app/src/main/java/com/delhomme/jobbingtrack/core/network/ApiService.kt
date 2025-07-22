package com.delhomme.jobbingtrack.core.network

import android.provider.ContactsContract.Profile
import com.delhomme.jobbingtrack.core.network.tokens.RefreshTokenRequest
import com.delhomme.jobbingtrack.features.authentication.domain.model.LoginRequest
import com.delhomme.jobbingtrack.features.authentication.domain.model.LoginResponse
import com.delhomme.jobbingtrack.features.authentication.domain.model.RegisterRequest
import com.delhomme.jobbingtrack.features.authentication.domain.model.RegisterResponse
import com.delhomme.jobbingtrack.features.profil.domain.model.ProfileResponse
import com.delhomme.jobbingtrack.features.user.domain.models.UserInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    // Authentification
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/auth/register/")
    suspend fun register(@Body body: RegisterRequest): Response<RegisterResponse>

    @POST("api/auth/refresh/")
    suspend fun refreshToken(@Body body: RefreshTokenRequest): Response<RefreshTokenResponse>

    // Profil utilisateur
    @GET("api/auth/me/")
    suspend fun getCurrentUser(): Response<UserInfo>

    @GET("api/profiles/")
    suspend fun getMyProfile(): Response<ProfileResponse>

    @POST("api/profiles/")
    suspend fun updateProfile(@Body profile: ProfileUpdateRequest): Response<ProfileResponse>

    // Synchronisation
    @GET("api/sync/")
    suspend fun syncData(@Query("updated_after") timestamp: Long): Response<SyncResponse>

    @POST("api/client-sync/")
    suspend fun clientSync(@Body data: Map<String, List<Any>>): Response<ClientSyncResponse>
}