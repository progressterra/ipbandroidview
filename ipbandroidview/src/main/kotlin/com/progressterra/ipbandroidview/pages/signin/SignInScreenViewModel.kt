package com.progressterra.ipbandroidview.pages.signin

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class SignInScreenViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val openUrlUseCase: OpenUrlUseCase
) : AbstractNonInputViewModel<SignInScreenState, SignInScreenEffect>(), UseSignInScreen {

    override fun createInitialState() = SignInScreenState()

    override fun handle(event: ButtonEvent) {
        onNext()
    }

    override fun handle(event: LinkTextEvent) {
        onBackground {
            openUrlUseCase(event.url)
        }
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.TextChanged -> {
                emitState { it.copy(phone = it.phone.copy(text = event.text)) }
                emitState { it.copy(auth = it.auth.copy(enabled = it.phone.valid())) }
            }

            is TextFieldEvent.Action -> onNext()
            is TextFieldEvent.AdditionalAction -> Unit
        }
    }

    private fun onNext() {
        onBackground {
            startVerificationChannelUseCase(currentState.phone.formatByType()).onSuccess {
                postEffect(SignInScreenEffect.Next(it))
            }
        }
    }
}
