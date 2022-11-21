package com.progressterra.ipbandroidview.ui.main

import androidx.annotation.StringRes

sealed class MainEffect {

    class Toast(@StringRes val message: Int) : MainEffect()
}