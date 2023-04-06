package com.progressterra.ipbandroidview.shared.ui.statebox

import com.progressterra.ipbandroidview.shared.ScreenState

fun Boolean.toStateBoxState() = if (this) StateBoxState(ScreenState.SUCCESS) else StateBoxState(
    ScreenState.ERROR
)