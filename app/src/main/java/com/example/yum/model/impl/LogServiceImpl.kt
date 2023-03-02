package com.example.yum.model.impl

import com.example.yum.model.service.LogService
import javax.inject.Inject

class LogServiceImpl @Inject constructor(): LogService {
    override fun logNonFatalCrash(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}