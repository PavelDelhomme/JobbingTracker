package com.delhomme.jobbingtrack.api.tokens

import com.delhomme.jobbingtrack.api.ApiService
import com.delhomme.jobbingtrack.api.RefreshTokenRequest
import com.delhomme.jobbingtrack.authentication.responses.LoginResponse
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val apiService: ApiService,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = tokenManager.getRefreshToken() ?: return null
        val newTokens = getNewTokens(refreshToken) ?: return null

        tokenManager.saveTokens(newTokens.access, newTokens.refresh)

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${newTokens.access}")
            .build()
    }

    private fun getNewTokens(refresh: String): LoginResponse? {
        return try {
            val call = apiService.refreshToken(RefreshTokenRequest(refresh))
            val resp = call.execute()
            if (resp.isSuccessful) {
                LoginResponse(access = resp.body()?.access ?: "", refresh = refresh)
            } else null
        } catch (e: Exception) {
            println("Error refreshing token : ${e.message}")
            null
        }
    }
}