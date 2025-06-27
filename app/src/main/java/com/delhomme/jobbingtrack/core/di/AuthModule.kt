package com.delhomme.jobbingtrack.core.di

import android.content.Context


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
