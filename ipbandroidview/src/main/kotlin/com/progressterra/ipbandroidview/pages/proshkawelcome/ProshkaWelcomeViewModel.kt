package com.progressterra.ipbandroidview.pages.proshkawelcome

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class ProshkaWelcomeViewModel : ViewModel(),
    ContainerHost<ProshkaWelcomeState, ProshkaWelcomeEvent>, UseProshkaWelcome {

    override val container =
        container<ProshkaWelcomeState, ProshkaWelcomeEvent>(
            ProshkaWelcomeState(
                authOrSkipState = AuthOrSkipState(
                    auth = ButtonState(
                        id = "next"
                    ), skip = ButtonState(
                        id = "skip"
                    )
                )
            )
        )

    override fun handle(event: ButtonEvent) = intent {
        when (event.id) {
            "next" -> when (event) {
                is ButtonEvent.Click -> postSideEffect(ProshkaWelcomeEvent.OnAuth)
            }
            "skip" -> when (event) {
                is ButtonEvent.Click -> postSideEffect(ProshkaWelcomeEvent.OnSkip)
            }
        }
    }
}