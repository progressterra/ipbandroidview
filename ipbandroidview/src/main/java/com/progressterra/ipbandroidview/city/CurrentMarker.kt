package com.progressterra.ipbandroidview.city

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.IsEmpty

data class CurrentMarker(
    val latLng: LatLng = LatLng(Double.MIN_VALUE, Double.MIN_VALUE)
) : IsEmpty {

    override fun isEmpty(): Boolean = latLng == LatLng(Double.MIN_VALUE, Double.MIN_VALUE)
}
