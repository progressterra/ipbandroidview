package com.progressterra.ipbandroidview.ui.checklist

import androidx.annotation.StringRes

sealed class ChecklistEffect {

    object Back : ChecklistEffect()

    @Suppress("unused")
    class Toast(@StringRes val message: Int) : ChecklistEffect()

    object RefreshAudits : ChecklistEffect()

    class Image(val id: String, val readOnly: Boolean) : ChecklistEffect()
}