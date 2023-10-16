package com.progressterra.ipbandroidview.integration

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.progressterra.ipbandroidview.pages.support.UpdateFirebaseCloudMessagingTokenUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.net.HttpURLConnection
import java.net.URL

abstract class CloudMessagingService : FirebaseMessagingService() {

    private val updateFcmTokenUseCase by inject<UpdateFirebaseCloudMessagingTokenUseCase>()
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    abstract val activityClass: Class<*>

    abstract val channelId: String

    abstract val notificationNameId: Int

    abstract val notificationIconId: Int

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val pendingIntent = configureIntent()
        val builder = configureBuilder(remoteMessage, pendingIntent)
        sendMessage(remoteMessage, builder)
    }

    private fun configureIntent(): PendingIntent {
        val intent = Intent(this, activityClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun configureBuilder(
        remoteMessage: RemoteMessage,
        pendingIntent: PendingIntent
    ): NotificationCompat.Builder {
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(notificationIconId)
            .setContentTitle(remoteMessage.notification?.title)
            .setContentText(remoteMessage.notification?.body)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_PROMO)

        remoteMessage.notification?.imageUrl?.let { imageUrl ->
            getBitmapFromUrl(imageUrl.toString())?.let {
                builder.setLargeIcon(it)
                builder.setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(it)
                )
            }
        }

        return builder
    }

    @SuppressLint("MissingPermission")
    private fun sendMessage(
        remoteMessage: RemoteMessage,
        builder: NotificationCompat.Builder
    ) {
        val manager = getSystemService(NOTIFICATION_SERVICE) as? NotificationManager

        val channelName = applicationContext.getString(notificationNameId)
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        manager?.createNotificationChannel(channel)

        with(NotificationManagerCompat.from(this)) {
            notify(remoteMessage.messageId.hashCode(), builder.build())
        }
    }

    private fun getBitmapFromUrl(imageUrl: String): Bitmap? {
        val url = URL(imageUrl)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input = connection.inputStream
        return BitmapFactory.decodeStream(input)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        scope.launch {
            updateFcmTokenUseCase(token)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}