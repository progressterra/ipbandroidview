package com.progressterra.ipbandroidview.base

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Tasks


interface ProvideLocation {

    suspend fun location(): Location

    class Base(private val fusedLocationClient: FusedLocationProviderClient) : ProvideLocation {

        override suspend fun location(): Location = Tasks.await(
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            )
        )
    }
}