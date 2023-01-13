package com.progressterra.ipbandroidview.core

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import kotlinx.coroutines.tasks.await

interface ProvideLocation {

    suspend fun location(): Result<Location>

    class Base(private val fusedLocationClient: FusedLocationProviderClient) : ProvideLocation {

        @SuppressLint("MissingPermission")
        override suspend fun location(): Result<Location> = runCatching {
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            ).await()
        }
    }
}