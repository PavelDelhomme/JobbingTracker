package com.delhomme.jobbingtrack.core.network.tokens

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(@ApplicationContext private val context: Context) {
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val prefs = EncryptedSharedPreferences.create(
        "secure_auth_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private var lastFailedToken: String? = null

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val USER_ID = "user_id"
        private const val TOKEN_EXPIRY = "token_expiry"
    }

    fun saveTokens(accessToken: String, refreshToken: String, expiryTimeInMillis: Long = System.currentTimeMillis() + 3600000) {
        prefs.edit()
            .putString(ACCESS_TOKEN, accessToken)
            .putString(REFRESH_TOKEN, refreshToken)
            .putLong(TOKEN_EXPIRY, expiryTimeInMillis)
            .apply()
    }

    fun saveUserId(userId: String) {
        prefs.edit().putString(USER_ID, userId).apply()
    }

    fun getAccessToken(): String? = prefs.getString(ACCESS_TOKEN, null)
    fun getRefreshToken(): String? = prefs.getString(REFRESH_TOKEN, null)
    fun getUserId(): String? = prefs.getString(USER_ID, null)
    fun getTokenExpiry(): Long = prefs.getLong(TOKEN_EXPIRY, 0)

    fun setLastFailedToken(token: String?) {
        lastFailedToken = token
    }

    fun getLastFailedToken(): String? = lastFailedToken

    fun isTokenExpired(): Boolean {
        val expiry = getTokenExpiry()
        return expiry > 0 && System.currentTimeMillis() > expiry
    }

    fun clearTokens() {
        prefs.edit()
            .remove(ACCESS_TOKEN)
            .remove(REFRESH_TOKEN)
            .remove(TOKEN_EXPIRY)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        val accessToken = getAccessToken()
        val refreshToken = getRefreshToken()

        return accessToken != null && refreshToken != null && !isTokenExpired()
    }

    fun logout() {
        clearTokens()
        prefs.edit().remove(USER_ID).apply()
    }
}