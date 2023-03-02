package com.example.yum.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}