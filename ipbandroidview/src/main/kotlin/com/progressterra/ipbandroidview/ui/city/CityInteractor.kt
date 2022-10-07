package com.progressterra.ipbandroidview.ui.city

import com.google.android.gms.maps.model.LatLng

interface CityInteractor {

    fun onSkip()

    fun onNext()

    fun onAddress(address: String)

    fun onMyLocation()

    fun onMapClick(latLng: LatLng)

    fun onSuggestion(suggestion: Suggestion)

    fun onAddressFocus(focused: Boolean)

    class Empty : CityInteractor {

        override fun onSkip() = Unit

        override fun onNext() = Unit

        override fun onAddress(address: String) = Unit

        override fun onMyLocation() = Unit

        override fun onMapClick(latLng: LatLng) = Unit

        override fun onSuggestion(suggestion: Suggestion) = Unit

        override fun onAddressFocus(focused: Boolean) = Unit
    }
}