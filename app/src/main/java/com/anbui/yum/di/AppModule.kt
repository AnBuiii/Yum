package com.anbui.yum.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.anbui.yum.data.remote.implement.UserInfoServiceImpl
import com.anbui.yum.data.remote.service.UserService
import com.anbui.yum.data.remote.implement.UserServiceImpl
import com.anbui.yum.data.remote.service.UserInfoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
//import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import javax.inject.Singleton
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json{
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }

        }
    }

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideUserService(client: HttpClient, prefs: SharedPreferences): UserService {
        return UserServiceImpl(prefs, client)
    }

    @Provides
    @Singleton
    fun provideUserInfoService(client: HttpClient, prefs: SharedPreferences): UserInfoService {
        return UserInfoServiceImpl(prefs, client)
    }


//    @Provides
//    @Singleton
//    fun provideChatSocketService(client: HttpClient): ChatSocketService {
//        return ChatSocketServiceImpl(client)
//    }
}