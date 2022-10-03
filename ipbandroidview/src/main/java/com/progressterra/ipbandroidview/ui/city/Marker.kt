package com.progressterra.ipbandroidview.ui.city

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.base.IsEmpty

data class Marker(
    val latLng: LatLng = LatLng(Double.MIN_VALUE, Double.MIN_VALUE)
) : IsEmpty {

    override fun isEmpty(): Boolean =
        latLng.latitude == Double.MIN_VALUE && latLng.longitude == Double.MIN_VALUE
}
