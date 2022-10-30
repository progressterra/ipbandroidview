package com.progressterra.ipbandroidview.ui.splash

sealed class SplashEffect {

    object OpenAuth : SplashEffect()

    object OpenMain : SplashEffect()
}
