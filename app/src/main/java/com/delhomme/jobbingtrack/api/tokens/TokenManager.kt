package com.delhomme.jobbingtrack.api.tokens

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

        private fun getPrefs(context: Context) =
            EncryptedSharedPreferences.create(
                FILE_NAME,
                MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

        // Méthodes statiques pour accès global
        fun getUserId(context: Context): String? {
            return getPrefs(context).getString(USER_ID, null)
        }

        fun getAccessToken(context: Context): String? {
            return getPrefs(context).getString(ACCESS_TOKEN, null)
        }
    }

    // Méthodes d'instance pour injection de dépendances
    fun saveTokens(access: String, refresh: String) {
        getPrefs(context).edit()
            .putString(ACCESS_TOKEN, access)
            .putString(REFRESH_TOKEN, refresh)
            .apply()
    }

    fun getAccessToken(): String? {
        return getPrefs(context).getString(ACCESS_TOKEN, null)
    }

    fun clearTokens() {
        getPrefs(context).edit().clear().apply()
    }

    fun saveUser(userId: String, token: String) {
        getPrefs(context).edit()
            .putString(USER_ID, userId)
            .putString(ACCESS_TOKEN, token)
            .apply()
    }

    fun getRefreshToken(): String? {
        return getPrefs(context).getString(REFRESH_TOKEN, null)
    }

    fun getUserId(): String? {
        return getPrefs(context).getString(USER_ID, null)
    }
}
