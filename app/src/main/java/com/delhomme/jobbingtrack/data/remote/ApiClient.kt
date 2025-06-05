package com.delhomme.jobbingtrack.data.remote

import android.content.Context
import com.delhomme.jobbingtrack.data.networks.AuthInterceptor
import com.delhomme.jobbingtrack.data.networks.TokenAuthenticator
import com.delhomme.jobbingtrack.data.networks.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    internal const val BASE_URL = "http://10.0.2.2:8000/api/"
    private lateinit var appContext: Context

    /** Appel unique, le plus tôt possible (Application#onCreate) */
    fun init(context: Context) {
        appContext = context.applicationContext
    }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .authenticator(TokenAuthenticator(appContext))
            .addInterceptor(AuthInterceptor(appContext))
            .build()
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
