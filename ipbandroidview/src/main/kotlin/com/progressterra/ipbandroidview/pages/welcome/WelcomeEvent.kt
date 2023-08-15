package com.progressterra.ipbandroidview.pages.welcome

sealed class WelcomeEvent {

    data object OnAuth : WelcomeEvent()

    data object OnSkip : WelcomeEvent()

    data object OnAlreadyAuth : WelcomeEvent()
}
