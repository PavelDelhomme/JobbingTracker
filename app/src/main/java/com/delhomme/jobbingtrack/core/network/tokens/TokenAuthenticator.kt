package com.delhomme.jobbingtrack.core.network.tokens

import com.delhomme.jobbingtrack.core.network.ApiService
import com.delhomme.jobbingtrack.features.authentication.domain.model.LoginResponse
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
        // Vérifier si c'est une demande de refresh token qui a échoué
        if (response.request.url.toString().contains("auth/refresh")) {
            // Si oui, c'est que le refresh token est invalide, on déconnecte l'utilisateur
            tokenManager.clearTokens()
            return null
        }

        val refreshToken = tokenManager.getRefreshToken() ?: return null

        synchronized(this) {
            // Vérifier à nouveau le token en cas d'exécution concurrente
            val currentToken = tokenManager.getAccessToken()
            if (currentToken != null && currentToken != tokenManager.getLastFailedToken()) {
                // Un autre thread a peut-être déjà rafraîchi le token
                return response.request.newBuilder()
                    .header("Authorization", "Bearer $currentToken")
                    .build()
            }

            // Tenter de rafraîchir le token
            try {
                val refreshRequest = RefreshTokenRequest(refreshToken)
                val refreshCall = apiService.refreshToken(refreshRequest)
                val refreshResponse = refreshCall.execute()

                if (refreshResponse.isSuccessful) {
                    val newTokens = refreshResponse.body()
                    if (newTokens != null) {
                        tokenManager.saveTokens(newTokens.access, newTokens.refresh)

                        // Reconstruire la requête avec le nouveau token
                        return response.request.newBuilder()
                            .header("Authorization", "Bearer ${newTokens.access}")
                            .build()
                    }
                } else {
                    // En cas d'échec, stocker le token qui a échoué
                    tokenManager.setLastFailedToken(tokenManager.getAccessToken())
                    tokenManager.clearTokens()
                }
            } catch (e: Exception) {
                // En cas d'erreur réseau, on conserve les tokens
                // mais on retourne null pour ne pas retenter immédiatement
                return null
            }
            return null
        }
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