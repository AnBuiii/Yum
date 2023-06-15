package com.anbui.yum.common.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.anbui.yum.common.notifcation.NotificationService

class AlarmReceiver() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        val id = intent.getIntExtra("ID",1)
        println("Alarm triggered: $message")
        val notificationService = NotificationService(context!!)
        notificationService.showNotification(message,id)

    }
}
