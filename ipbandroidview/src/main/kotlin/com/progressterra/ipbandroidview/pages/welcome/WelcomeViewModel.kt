package com.progressterra.ipbandroidview.pages.welcome

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class WelcomeViewModel : ViewModel(),
    ContainerHost<WelcomeState, WelcomeEvent>, UseWelcome {

    override val container =
        container<WelcomeState, WelcomeEvent>(
            WelcomeState(
                auth = ButtonState(
                    id = "next"
                )
            )
        )

    fun refresh() {
        intent {
            if (UserData.clientExist) postSideEffect(WelcomeEvent.OnAlreadyAuth)
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "next" -> postSideEffect(WelcomeEvent.OnAuth)

                "skip" -> postSideEffect(WelcomeEvent.OnSkip)
            }
        }
    }
}