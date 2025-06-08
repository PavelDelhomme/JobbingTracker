package com.delhomme.jobbingtrack.api

import android.content.Context
import com.delhomme.jobbingtrack.api.tokens.TokenManager
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = TokenManager.getTokens(context)
        val req = chain.request().newBuilder()
            .apply { token?.let { header("Authorization", "Bearer $it") } }
            .build()
        return chain.proceed(req)
    }
}