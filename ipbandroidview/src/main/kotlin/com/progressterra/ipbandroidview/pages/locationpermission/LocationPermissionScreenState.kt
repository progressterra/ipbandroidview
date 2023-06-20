package com.progressterra.ipbandroidview.pages.locationpermission

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class LocationPermissionScreenState(
    val give: ButtonState = ButtonState(id = "give"),
    val skip: ButtonState = ButtonState(id = "skip")
)