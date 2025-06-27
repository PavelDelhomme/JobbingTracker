package com.delhomme.jobbingtrack.core.di

import android.content.Context
import com.delhomme.jobbingtrack.core.network.ApiService
import com.delhomme.jobbingtrack.core.network.tokens.TokenAuthenticator
import com.delhomme.jobbingtrack.core.network.tokens.TokenManager
import com.delhomme.jobbingtrack.features.authentication.data.repositories.LoginRepository
import com.delhomme.jobbingtrack.features.authentication.data.repositories.RegisterRepository
import com.delhomme.jobbingtrack.services.api.LoginService
import com.delhomme.jobbingtrack.services.api.RegisterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


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
