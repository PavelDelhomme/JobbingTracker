package com.delhomme.jobbingtrack.data.networks

import android.content.Context
import com.delhomme.jobbingtrack.data.models.LoginResponse
import com.delhomme.jobbingtrack.data.remote.ApiClient
import com.delhomme.jobbingtrack.data.remote.ApiService
import com.delhomme.jobbingtrack.data.remote.RefreshTokenRequest
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TokenAuthenticator(private val context: Context) : Authenticator {


    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = TokenManager.getRefreshToken(context) ?: return null
        val newTokens = getNewTokens(refreshToken) ?: return null

        TokenManager.saveTokens(context, newTokens.access, newTokens.refresh)

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${newTokens.access}")
            .build()
    }

    private fun getNewTokens(refresh: String): LoginResponse? {
        return try {
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(ApiService::class.java)
            val call = service.refreshToken(RefreshTokenRequest(refresh))
            val resp = call.execute()
            if (resp.isSuccessful) {
                // Retourne un objet LoginResponse avec *refresh* inclus
                LoginResponse(access = resp.body()?.access ?: "", refresh = refresh)
            } else null
        } catch (e: Exception) {
            null
        }
    }
}
