package com.delhomme.jobbingtrack.data.remote

import android.content.Context
import com.delhomme.jobbingtrack.data.networks.AuthInterceptor
import com.delhomme.jobbingtrack.data.networks.TokenAuthenticator
import com.delhomme.jobbingtrack.data.networks.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    internal const val BASE_URL = "https://ton-backend.com/api/" // temporairement pour dev

    // A initialiser depuis votre Application ou via DI pour avoir le Context
    private lateinit var appContext: Context

    /** Appel unique, le plus tôt possible (Application#onCreate) */
    fun init(context: Context) {
        appContext = context.applicationContext
    }

    private val okHttp = OkHttpClient.Builder()
        /*.addInterceptor { chain ->
            val token = TokenManager.getTokens(appContext)
            val req = chain.request().newBuilder()
                .apply { token?.let { header("Authorization", "Bearer $it") } }
                .build()
            chain.proceed(req)
        }*/
        .authenticator(TokenAuthenticator(appContext)) // 🔁 auto-refresh
        .addInterceptor(AuthInterceptor(appContext)) // 🛡️ ajoute le token aux requêtes
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
