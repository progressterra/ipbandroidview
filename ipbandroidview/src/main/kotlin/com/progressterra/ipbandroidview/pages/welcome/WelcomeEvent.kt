package com.progressterra.ipbandroidview.pages.welcome

sealed class WelcomeEvent {

    object OnAuth : WelcomeEvent()

    object OnSkip : WelcomeEvent()

    object OnAlreadyAuth : WelcomeEvent()
}
