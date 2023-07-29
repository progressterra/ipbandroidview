package com.progressterra.ipbandroidview.pages.welcome

import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent

class WelcomeViewModel : BaseViewModel<WelcomeState, WelcomeEvent>(), UseWelcome {

    override val initialState = WelcomeState()

    fun refresh() {
        if (UserData.clientExist) postEffect(WelcomeEvent.OnAlreadyAuth)
    }

    override fun handle(event: ButtonEvent) {
        when (event.id) {
            "next" -> postEffect(WelcomeEvent.OnAuth)
            "skip" -> postEffect(WelcomeEvent.OnSkip)
        }
    }
}