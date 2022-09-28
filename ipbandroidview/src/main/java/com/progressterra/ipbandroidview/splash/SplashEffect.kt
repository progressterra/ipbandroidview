package com.progressterra.ipbandroidview.splash

sealed class SplashEffect {

    object Auth : SplashEffect()

    object NonAuth : SplashEffect()
}
