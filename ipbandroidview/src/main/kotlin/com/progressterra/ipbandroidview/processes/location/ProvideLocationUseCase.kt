package com.progressterra.ipbandroidview.processes.location

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import kotlinx.coroutines.tasks.await

interface ProvideLocationUseCase {

    suspend operator fun invoke(): Result<Location>

    class Base(private val fusedLocationClient: FusedLocationProviderClient) : ProvideLocationUseCase {

        @SuppressLint("MissingPermission")
        override suspend fun invoke(): Result<Location> = runCatching {
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            ).await()
        }
    }
}