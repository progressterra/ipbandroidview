package com.progressterra.ipbandroidview.pages.locationpermission

import com.progressterra.ipbandroidview.shared.ui.button.ButtonState


data class LocationPermissionScreenState(
    val give: ButtonState = ButtonState(id = "give"),
    val skip: ButtonState = ButtonState(id = "skip")
)