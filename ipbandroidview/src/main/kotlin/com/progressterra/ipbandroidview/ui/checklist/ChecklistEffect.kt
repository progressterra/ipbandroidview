package com.progressterra.ipbandroidview.ui.checklist

import androidx.annotation.StringRes


sealed class ChecklistEffect {

    object OnBack : ChecklistEffect()

    @Suppress("unused")
    class OnToast(@StringRes val message: Int) : ChecklistEffect()
}