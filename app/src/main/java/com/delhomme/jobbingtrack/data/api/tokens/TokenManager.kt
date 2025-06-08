package com.delhomme.jobbingtrack.data.api.tokens

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object TokenManager {

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

    fun saveTokens(context: Context, access: String, refresh: String) {
        getPrefs(context).edit()
            .putString(ACCESS_TOKEN, access)
            .putString(REFRESH_TOKEN, refresh)
            .apply()
    }

    fun getTokens(context: Context): String? {
        return getPrefs(context).getString(ACCESS_TOKEN, null)
    }

    fun clearTokens(context: Context) {
        getPrefs(context).edit().clear().apply()
    }

    fun saveUser(context: Context, userId: String, token: String) {
        getPrefs(context).edit()
            .putString(USER_ID, userId)
            .putString(ACCESS_TOKEN, token)
            .apply()
    }

    fun getRefreshToken(context: Context): String? {
        return getPrefs(context).getString(REFRESH_TOKEN, null)
    }

    fun getUserId(context: Context): String? {
        return getPrefs(context).getString(USER_ID, null)
    }
}