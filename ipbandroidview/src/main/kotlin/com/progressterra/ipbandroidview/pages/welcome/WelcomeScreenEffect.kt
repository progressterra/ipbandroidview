package com.progressterra.ipbandroidview.pages.welcome

sealed class WelcomeScreenEffect {

    data object OnAuth : WelcomeScreenEffect()

    data object OnSkip : WelcomeScreenEffect()

    data object OnAlreadyAuth : WelcomeScreenEffect()
}
