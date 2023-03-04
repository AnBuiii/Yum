package com.example.yum.model.service.module

import com.example.yum.model.service.impl.UserServiceImpl
import com.example.yum.model.service.impl.ConfigurationServiceImpl
import com.example.yum.model.service.impl.LogServiceImpl
import com.example.yum.model.service.impl.RecipeServiceImpl
import com.example.yum.model.service.UserService
import com.example.yum.model.service.ConfigurationService
import com.example.yum.model.service.LogService
import com.example.yum.model.service.RecipeService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
 abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: UserServiceImpl): UserService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideStorageService(impl: RecipeServiceImpl): RecipeService

    @Binds
    abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService
}