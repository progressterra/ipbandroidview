package com.progressterra.ipbandroidview.ui.checklist

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.model.MultisizedImage

sealed class ChecklistEffect {

    object Back : ChecklistEffect()

    object AddEmail : ChecklistEffect()

    class Toast(@StringRes val message: Int) : ChecklistEffect()

    class OpenImage(val picture: MultisizedImage, val enabled: Boolean) : ChecklistEffect()
}