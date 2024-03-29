package com.anbui.yum.common.notifcation

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.anbui.yum.MainApplication
import com.anbui.yum.R

class NotificationService(
    private val context: Context,
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(message: String, id: Int) {
        val activityIntent = Intent(
            context,
            MainApplication::class.java,
        )
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        val notification = NotificationCompat.Builder(
            context,
            COUNTER_CHANNEL_ID,
        )
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Let's cook!!")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(activityPendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(
            id,
            notification.build(),
        )
    }

    companion object {
        const val COUNTER_CHANNEL_ID = "counter_channel"
    }
}
