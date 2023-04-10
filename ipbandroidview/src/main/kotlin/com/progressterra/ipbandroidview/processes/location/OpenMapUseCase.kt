package com.progressterra.ipbandroidview.processes.location

import android.content.Intent
import android.net.Uri
import com.progressterra.ipbandroidview.shared.activity.StartActivityContract

interface OpenMapUseCase {

    suspend operator fun invoke(latitude: Double, longitude: Double)

    class Base(
        private val startActivityContract: StartActivityContract.Client
    ) : OpenMapUseCase {

        override suspend fun invoke(latitude: Double, longitude: Double) {
            val mapIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("geo:${latitude},${longitude}"))
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivityContract.start(mapIntent)
        }
    }
}