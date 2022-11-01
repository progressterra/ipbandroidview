package com.progressterra.ipbandroidview.ui.main

import androidx.annotation.StringRes

sealed class MainEffect {

    class OpenDetails(val id: String) : MainEffect()

    @Suppress("unused")
    class Toast(@StringRes val message: Int) : MainEffect()
}