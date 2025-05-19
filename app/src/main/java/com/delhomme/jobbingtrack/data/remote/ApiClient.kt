package com.delhomme.jobbingtrack.data.remote

import android.content.Context
import com.delhomme.jobbingtrack.data.networks.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private const val BASE_URL = "https://ton-backend.com/api/" // temporairement pour dev

    // A initialiser depuis votre Application ou via DI pour avoir le Context
    private lateinit var appContext: Context

    /** Appel unique, le plus tôt possible (Application#onCreate) */
    fun init(context: Context) {
        appContext = context.applicationContext
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val token = TokenManager.getToken(appContext)
            val req = chain.request().newBuilder()
                .apply { token?.let { header("Authorization", "Bearer $it") } }
                .build()
            chain.proceed(req)
        }
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
