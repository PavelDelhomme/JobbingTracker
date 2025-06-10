package com.delhomme.jobbingtrack.di

import android.content.Context
import com.delhomme.jobbingtrack.api.ApiClient
import com.delhomme.jobbingtrack.api.ApiService
import com.delhomme.jobbingtrack.api.authentication.repositories.LoginRepository
import com.delhomme.jobbingtrack.api.authentication.repositories.RegisterRepository
import com.delhomme.jobbingtrack.api.authentication.services.LoginService
import com.delhomme.jobbingtrack.api.authentication.services.RegisterService
import com.delhomme.jobbingtrack.api.tokens.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiClient.api

    @Provides
    @Singleton
    fun provideLoginService(apiService: ApiService): LoginService =
        LoginService(apiService)

    @Provides
    @Singleton
    fun provideRegisterService(apiService: ApiService): RegisterService =
        RegisterService(apiService)

    @Provides
    @Singleton
    fun provideLoginRepository(loginService: LoginService): LoginRepository =
        LoginRepository(loginService)

    @Provides
    @Singleton
    fun provideRegisterRepository(registerService: RegisterService): RegisterRepository =
        RegisterRepository(registerService)

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager =
        TokenManager(context)

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context
}
