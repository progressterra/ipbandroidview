package com.progressterra.ipbandroidview.integration

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import com.progressterra.ipbandroidview.shared.log

abstract class GeofencingReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        if (geofencingEvent == null) {
            log("Geofencing", "event is null")
            return
        }
        if (geofencingEvent.hasError()) {
            log("Geofencing", "error: ${geofencingEvent.errorCode}")
            return
        }
        val geofenceTransition = geofencingEvent.geofenceTransition
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            log("Geofencing", "enter")
        } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            log("Geofencing", "exit")
        }
    }
}