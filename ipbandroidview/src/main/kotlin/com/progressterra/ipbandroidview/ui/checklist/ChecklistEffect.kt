package com.progressterra.ipbandroidview.ui.checklist

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.core.Picture

sealed class ChecklistEffect {

    object Back : ChecklistEffect()

    @Suppress("unused")
    class ShowToast(@StringRes val message: Int) : ChecklistEffect()

    object RefreshAudits : ChecklistEffect()

    @Suppress("unused")
    class OpenImage(val picture: Picture, val enabled: Boolean) : ChecklistEffect()
}