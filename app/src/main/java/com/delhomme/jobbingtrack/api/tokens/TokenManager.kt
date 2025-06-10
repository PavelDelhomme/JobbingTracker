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
    private val FILE_NAME = "auth_prefs"
    private val ACCESS_TOKEN = "access_token"
    private val REFRESH_TOKEN = "refresh_token"
    private val USER_ID = "user_id"


    private fun getPrefs(context: Context) =
        EncryptedSharedPreferences.create(
            FILE_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun saveTokens(access: String, refresh: String) {
        getPrefs(this.context).edit()
            .putString(ACCESS_TOKEN, access)
            .putString(REFRESH_TOKEN, refresh)
            .apply()
    }

    fun getTokens(): String? {
        return getPrefs(this.context).getString(ACCESS_TOKEN, null)
    }

    fun clearTokens() {
        getPrefs(this.context).edit().clear().apply()
    }

    fun saveUser(userId: String, token: String) {
        getPrefs(this.context).edit()
            .putString(USER_ID, userId)
            .putString(ACCESS_TOKEN, token)
            .apply()
    }

    fun getRefreshToken(): String? {
        return getPrefs(this.context).getString(REFRESH_TOKEN, null)
    }

    fun getUserId(): String? {
        return getPrefs(this.context).getString(USER_ID, null)
    }
}