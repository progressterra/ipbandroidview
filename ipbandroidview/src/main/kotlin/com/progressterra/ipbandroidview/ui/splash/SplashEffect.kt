package com.progressterra.ipbandroidview.ui.splash

sealed class SplashEffect {

    object Auth : SplashEffect()

    object NonAuth : SplashEffect()
}
