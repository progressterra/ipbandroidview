package com.progressterra.ipbandroidview.ui.main

import androidx.annotation.StringRes

@Suppress("unused")
sealed class MainEffect {

    class Toast(@StringRes val message: Int) : MainEffect()
}