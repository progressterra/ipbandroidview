package com.progressterra.ipbandroidview.ui.checklist

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.core.Photo

sealed class ChecklistEffect {

    object Back : ChecklistEffect()

    @Suppress("unused")
    class Toast(@StringRes val message: Int) : ChecklistEffect()

    object RefreshAudits : ChecklistEffect()

    class Image(val photo: Photo) : ChecklistEffect()
}