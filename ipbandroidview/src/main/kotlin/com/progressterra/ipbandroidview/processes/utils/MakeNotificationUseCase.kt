package com.progressterra.ipbandroidview.processes.utils

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

interface MakeNotificationUseCase {

    operator fun invoke(title: String, msg: String)

    class Base(
        private val context: Context,
        private val iconId: Int,
        private val channelId: String,
        private val channelName: String,
        private val activity: Class<*>
    ) : MakeNotificationUseCase {

        @SuppressLint("MissingPermission")
        override fun invoke(title: String, msg: String) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager: NotificationManager =
                getSystemService(context, NotificationManager::class.java)!!
            notificationManager.createNotificationChannel(channel)
            val intent = Intent(context, activity).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(iconId)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
            val notificationId = System.currentTimeMillis().toInt()
            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, builder.build())
            }
        }
    }
}