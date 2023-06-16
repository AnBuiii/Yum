package com.anbui.yum

import android.app.Application
import com.anbui.yum.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.anbui.yum.common.notifcation.NotificationService


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }

        createNotificationChannel()
    }


    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationService.COUNTER_CHANNEL_ID,
            "Counter",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "Used for the increment counter notifications"

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)


    }
}
