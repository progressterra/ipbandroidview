package com.progressterra.ipbandroidview.ui.city

import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.base.IsEmpty

data class MapMarker(
    val latLng: LatLng? = null
) : IsEmpty {

    override fun isEmpty(): Boolean = latLng == null
}
