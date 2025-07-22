package com.delhomme.jobbingtrack.core.network.tokens

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val FILE_NAME = "auth_prefs"
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val USER_ID = "user_id"
        private const val TOKEN_EXPIRY = "token_expiry"
        private const val LAST_FAILED_TOKEN = "last_failed_token"

        // Méthodes statiques pour accès global
        fun getUserId(context: Context): String? {
            return getPrefs(context).getString(USER_ID, null)
        }

        fun getAccessToken(context: Context): String? {
            return getPrefs(context).getString(ACCESS_TOKEN, null)
        }

        fun getPrefs(context: Context) =
            EncryptedSharedPreferences.create(
                FILE_NAME,
                MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    }

    // Méthodes d'instance pour injection de dépendances
    fun saveTokens(access: String, refresh: String) {
        // Calculer une expiration approximative du token (15 minutes)
        val expiresAt = System.currentTimeMillis() + (15 * 60 * 1000)

        getPrefs(context).edit()
            .putString(ACCESS_TOKEN, access)
            .putString(REFRESH_TOKEN, refresh)
            .putLong(TOKEN_EXPIRY, expiresAt)
            .apply()
    }

    fun getAccessToken(): String? {
        val prefs = getPrefs(context)
        val token = prefs.getString(ACCESS_TOKEN, null)
        val expiry = prefs.getLong(TOKEN_EXPIRY, 0)

        // Si le token est expiré localement, retourner null pour forcer un refresh
        return if (token != null && System.currentTimeMillis() < expiry) token else null
    }


    fun getRefreshToken(): String? {
        return getPrefs(context).getString(REFRESH_TOKEN, null)
    }

    fun clearTokens() {
        getPrefs(context).edit()
            .remove(ACCESS_TOKEN)
            .remove(REFRESH_TOKEN)
            .remove(TOKEN_EXPIRY)
            .apply()
    }

    fun saveUser(userId: String, token: String) {
        getPrefs(context).edit()
            .putString(USER_ID, userId)
            .putString(ACCESS_TOKEN, token)
            .apply()
    }

    fun getUserId(): String? {
        return getPrefs(context).getString(USER_ID, null)
    }

    fun isLoggedIn(): Boolean {
        return getRefreshToken() != null
    }

    fun setLastFailedToken(token: String?) {
        getPrefs(context).edit()
            .putString(LAST_FAILED_TOKEN, token)
            .apply()
    }

    fun getLastFailedToken(): String? {
        return getPrefs(context).getString(LAST_FAILED_TOKEN, null)
    }
}
