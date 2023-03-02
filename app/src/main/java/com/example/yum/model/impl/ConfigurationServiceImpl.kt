package com.example.yum.model.impl

import com.example.yum.BuildConfig
import com.example.yum.model.service.ConfigurationService
import com.example.yum.model.service.trace
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.example.yum.R.xml as AppConfig

class ConfigurationServiceImpl  @Inject constructor(): ConfigurationService {
    private val remoteConfig
        get() = Firebase.remoteConfig

    override suspend fun fetchConfiguration(): Boolean =
        trace(FETCH_CONFIG_TRACE) { remoteConfig.fetchAndActivate().await() }

    companion object {
        private const val FETCH_CONFIG_TRACE = "fetchConfig"
    }
}