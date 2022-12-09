package com.progressterra.ipbandroidview.ui.checklist

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.model.media.MultisizedImage

sealed class ChecklistEffect {

    object Back : ChecklistEffect()

    class Toast(@StringRes val message: Int) : ChecklistEffect()

    class OpenImage(val picture: MultisizedImage, val enabled: Boolean) : ChecklistEffect()
}