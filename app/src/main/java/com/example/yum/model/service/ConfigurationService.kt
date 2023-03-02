package com.example.yum.model.service

interface ConfigurationService
{
    suspend fun fletchConfiguration(): Boolean
}