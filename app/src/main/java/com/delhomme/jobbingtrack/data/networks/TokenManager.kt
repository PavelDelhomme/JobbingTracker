package com.delhomme.jobbingtrack.data.networks

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object TokenManager {

    private const val FILE_NAME = "auth_prefs"
    private const val ACCESS_TOKEN = "access_token"
    private const val USER_ID = "user_id"

    private fun getPrefs(context: Context) =
        EncryptedSharedPreferences.create(
            FILE_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun saveToken(context: Context, token: String) {
        getPrefs(context).edit().putString(ACCESS_TOKEN, token).apply()
    }

    fun getToken(context: Context): String? {
        return getPrefs(context).getString(ACCESS_TOKEN, null)
    }

    fun clearToken(context: Context) {
        getPrefs(context).edit().clear().apply()
    }

    fun saveUser(context: Context, userId: String, token: String) {
        getPrefs(context).edit()
            .putString(USER_ID, userId)
            .putString(ACCESS_TOKEN, token)
            .apply()
    }

    fun getUserId(context: Context): String? {
        return getPrefs(context).getString(USER_ID, null)
    }
}