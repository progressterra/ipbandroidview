package com.progressterra.ipbandroidview.ui.city

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.model.address.SuggestionUI

interface CityInteractor {

    fun onBack()

    fun onSkip()

    fun onNext()

    fun editAddress(address: String)

    fun mapClick(latLng: LatLng)

    fun myLocationClick(location: Location)

    fun onSuggestion(suggestion: SuggestionUI)

    class Empty : CityInteractor {

        override fun onBack() = Unit

        override fun onSkip() = Unit

        override fun onNext() = Unit

        override fun editAddress(address: String) = Unit

        override fun mapClick(latLng: LatLng) = Unit

        override fun myLocationClick(location: Location) = Unit

        override fun onSuggestion(suggestion: SuggestionUI) = Unit
    }
}