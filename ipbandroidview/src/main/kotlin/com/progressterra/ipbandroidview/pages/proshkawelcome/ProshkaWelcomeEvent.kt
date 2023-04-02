package com.progressterra.ipbandroidview.pages.proshkawelcome

sealed class ProshkaWelcomeEvent {

    object OnAuth : ProshkaWelcomeEvent()

    object OnSkip : ProshkaWelcomeEvent()
}
