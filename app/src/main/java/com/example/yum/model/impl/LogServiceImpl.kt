package com.example.yum.model.impl

import com.example.yum.model.service.LogService
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class LogServiceImpl @Inject constructor(): LogService {
    override fun logNonFatalCrash(throwable: Throwable) =
        Firebase.crashlytics.recordException(throwable)
}