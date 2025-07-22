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
    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    private var lastFailedToken: String? = null

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val USER_ID = "user_id"
    }

    fun saveTokens(accessToken: String, refreshToken: String) {
        prefs.edit()
            .putString(ACCESS_TOKEN, accessToken)
            .putString(REFRESH_TOKEN, refreshToken)
            .apply()
    }
    fun saveUserId(userId: String) {
        prefs.edit().putString(USER_ID, userId).apply()
    }

    fun getAccessToken(): String? = prefs.getString(ACCESS_TOKEN, null)
    fun getRefreshToken(): String? = prefs.getString(REFRESH_TOKEN, null)
    fun getUserId(): String? = prefs.getString(USER_ID, null)

    fun setLastFailedToken(token: String?) {
        lastFailedToken = token
    }

    fun getLastFailedToken(): String? = lastFailedToken

    fun clearTokens() {
        prefs.edit()
            .remove(ACCESS_TOKEN)
            .remove(REFRESH_TOKEN)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        return getAccessToken() != null && getRefreshToken() != null
    }

    fun logout() {
        clearTokens()
        prefs.edit().remove(USER_ID).apply()
    }
}
