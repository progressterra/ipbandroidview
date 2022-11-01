package com.progressterra.ipbandroidview.core

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Tasks

interface ProvideLocation {

    suspend fun location(): Result<Location>

    class Base(private val fusedLocationClient: FusedLocationProviderClient) : ProvideLocation {

        override suspend fun location(): Result<Location> = try {
            Result.success(
                Tasks.await(
                    fusedLocationClient.getCurrentLocation(
                        Priority.PRIORITY_HIGH_ACCURACY,
                        null
                    )
                )
            )
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}