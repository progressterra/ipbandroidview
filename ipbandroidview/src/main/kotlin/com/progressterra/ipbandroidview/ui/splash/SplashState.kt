package com.progressterra.ipbandroidview.ui.splash

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.R

@Immutable
data class SplashState(
    @DrawableRes val logoId: Int = R.drawable.splash_logo
)
