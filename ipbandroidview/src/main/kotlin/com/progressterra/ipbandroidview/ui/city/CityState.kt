package com.progressterra.ipbandroidview.ui.city

import com.progressterra.ipbandroidview.composable.component.MapState

data class CityState(
    val isDataValid: Boolean = false,
    val mapState: MapState = MapState()
)
