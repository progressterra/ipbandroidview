package com.progressterra.ipbandroidview.ui.city

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Next
import com.progressterra.ipbandroidview.actions.Skip

interface CityInteractor : Back, Next, Skip {

    fun editAddress(address: String)

    fun onMyLocation()

    fun onMapClick(latLng: LatLng)

    fun onSuggestion(suggestion: Suggestion)

    fun onAddressFocus(focused: Boolean)

    class Empty : CityInteractor {

        override fun back() = Unit

        override fun skip() = Unit

        override fun next() = Unit

        override fun editAddress(address: String) = Unit

        override fun onMyLocation() = Unit

        override fun onMapClick(latLng: LatLng) = Unit

        override fun onSuggestion(suggestion: Suggestion) = Unit

        override fun onAddressFocus(focused: Boolean) = Unit
    }
}