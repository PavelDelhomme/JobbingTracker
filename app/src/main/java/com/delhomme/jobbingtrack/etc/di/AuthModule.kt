package com.delhomme.jobbingtrack.etc.di

import android.content.Context
import com.delhomme.jobbingtrack.api.ApiService
import com.delhomme.jobbingtrack.api.tokens.TokenAuthenticator
import com.delhomme.jobbingtrack.api.tokens.TokenManager
import com.delhomme.jobbingtrack.services.LoginService
import com.delhomme.jobbingtrack.services.RegisterService
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
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager =
        TokenManager(context)

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
    fun provideTokenAuthenticator(tokenManager: TokenManager, apiService: ApiService): TokenAuthenticator {
        return TokenAuthenticator(tokenManager, apiService)
    }
}
