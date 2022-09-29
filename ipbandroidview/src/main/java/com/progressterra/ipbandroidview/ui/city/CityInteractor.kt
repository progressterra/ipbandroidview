package com.progressterra.ipbandroidview.ui.city

import com.google.android.gms.maps.model.LatLng

interface CityInteractor {

    fun onBack()

    fun onSkip()

    fun onNext()

    fun onAddress(address: String)

    fun onMyLocation()

    fun onMapClick(latLng: LatLng)

    class Empty : CityInteractor {

        override fun onBack() = Unit

        override fun onSkip() = Unit

        override fun onNext() = Unit

        override fun onAddress(address: String) = Unit

        override fun onMyLocation() = Unit

        override fun onMapClick(latLng: LatLng) = Unit
    }
}