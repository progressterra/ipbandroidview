package com.progressterra.ipbandroidview.pages.welcome

import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent

class WelcomeScreenViewModel : AbstractNonInputViewModel<WelcomeScreenState, WelcomeScreenEffect>(),
    UseWelcomeScreen {

    override fun createInitialState() = WelcomeScreenState()

    override fun handle(event: ButtonEvent) {
        when (event.id) {
            "auth" -> postEffect(WelcomeScreenEffect.OnAuth)
            "skip" -> postEffect(WelcomeScreenEffect.OnSkip)
        }
    }
}