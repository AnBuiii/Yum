package com.example.yum.model.impl

import com.example.yum.model.service.ConfigurationService
import javax.inject.Inject

class ConfigurationServiceImpl  @Inject constructor(): ConfigurationService {
    override suspend fun fletchConfiguration(): Boolean {
        TODO("Not yet implemented")
    }
}