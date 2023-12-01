package com.progressterra.ipbandroidview.processes.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.progressterra.ipbandroidview.entities.Fence
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeNotificationUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.shared.log
import com.progressterra.ipbandroidview.shared.mvi.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface SetupGeofencesUseCase {

    suspend operator fun invoke(fences: List<Fence>)

    class Base(
        private val geofencingClient: GeofencingClient,
        private val checkPermissionUseCase: CheckPermissionUseCase,
        private val context: Context,
        private val receiverClass: Class<*>,
        private val makeNotificationUseCase: MakeNotificationUseCase,
        manageResources: ManageResources,
        makeToastUseCase: MakeToastUseCase
    ) : SetupGeofencesUseCase, AbstractLoggingUseCase(makeToastUseCase, manageResources) {

        @SuppressLint("MissingPermission")
        override suspend fun invoke(fences: List<Fence>) {
            handle {
                val geofences = fences.map {
                    Geofence.Builder()
                        .setRequestId(it.id)
                        .setCircularRegion(
                            it.latitude,
                            it.longitude,
                            it.radius
                        )
                        .setExpirationDuration(Geofence.NEVER_EXPIRE)
                        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                        .build()
                }
                val request = GeofencingRequest.Builder()
                    .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                    .addGeofences(geofences)
                    .build()
                val intent = Intent(context, receiverClass)
                val pendingIntent =
                    PendingIntent.getBroadcast(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
                    )
                checkPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION).throwOnFailure()
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    checkPermissionUseCase(Manifest.permission.ACCESS_BACKGROUND_LOCATION).throwOnFailure()
                }
                geofencingClient.addGeofences(request, pendingIntent).run {
                    addOnSuccessListener {
                        makeNotificationUseCase("Geofencing", "Geofencing began")
                    }
                    addOnFailureListener {
                        log("Geofencing", "failure with $it")
                    }
                }
            }
        }
    }
}