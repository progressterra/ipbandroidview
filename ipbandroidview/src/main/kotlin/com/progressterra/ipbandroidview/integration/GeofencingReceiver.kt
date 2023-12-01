package com.progressterra.ipbandroidview.integration

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import com.progressterra.ipbandroidview.processes.utils.MakeNotificationUseCase
import com.progressterra.ipbandroidview.shared.log

abstract class GeofencingReceiver : BroadcastReceiver() {

    abstract val iconId: Int

    abstract val channelId: String

    abstract val channelName: String

    abstract val activity: Class<*>

    override fun onReceive(context: Context, intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent) ?: return
        if (geofencingEvent.hasError()) {
            log("Geofencing", "error: ${geofencingEvent.errorCode}")
            return
        }
        val geofenceTransition = geofencingEvent.geofenceTransition
        val makeNotificationUseCase = MakeNotificationUseCase.Base(
            context = context,
            iconId = iconId,
            channelId = channelId,
            channelName = channelName,
            activity = activity
        )
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            makeNotificationUseCase("Geofencing", "Entering ${geofencingEvent.triggeringGeofences?.map { it.requestId }}")
        } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            makeNotificationUseCase("Geofencing", "Exiting ${geofencingEvent.triggeringGeofences?.map { it.requestId }}")
        }
    }
}