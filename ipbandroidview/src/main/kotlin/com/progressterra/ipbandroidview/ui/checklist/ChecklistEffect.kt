package com.progressterra.ipbandroidview.ui.checklist

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.model.CheckPicture

@Suppress("unused")
sealed class ChecklistEffect {

    object Back : ChecklistEffect()

    class Toast(@StringRes val message: Int) : ChecklistEffect()

    class OpenImage(val picture: CheckPicture, val enabled: Boolean) : ChecklistEffect()
}