package com.progressterra.ipbandroidview.ui.checklist

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.model.CheckPicture

sealed class ChecklistEffect {

    object Back : ChecklistEffect()

    @Suppress("unused")
    class Toast(@StringRes val message: Int) : ChecklistEffect()

    @Suppress("unused")
    class OpenImage(val picture: CheckPicture, val enabled: Boolean) : ChecklistEffect()
}