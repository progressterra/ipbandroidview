package com.progressterra.ipbandroidview.ui.city

import com.progressterra.ipbandroidview.composable.component.MapComponentState

data class CityState(
    val isDataValid: Boolean = false,
    val mapComponentState: MapComponentState = MapComponentState()
)
