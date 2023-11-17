package com.progressterra.ipbandroidview.pages.targetpicker

import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseTargetPickerScreen : UseTopBar, UseButton, UseStateColumn {

    fun handle(event: TargetPickerScreenEvent)
}