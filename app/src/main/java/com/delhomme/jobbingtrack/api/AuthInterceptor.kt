package com.delhomme.jobbingtrack.api

import android.content.Context
import com.delhomme.jobbingtrack.api.tokens.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor(private val context: Context) : Interceptor {
    @Inject lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenManager.getAccessToken()
        val req = chain.request().newBuilder()
            .apply { token?.let { header("Authorization", "Bearer $it") } }
            .build()
        return chain.proceed(req)
    }
}