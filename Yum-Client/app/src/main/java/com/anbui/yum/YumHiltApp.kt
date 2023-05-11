package com.anbui.yum

import android.app.Application
import com.anbui.yum.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class YumHiltApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@YumHiltApp)
            modules(appModule)
        }
    }
}
