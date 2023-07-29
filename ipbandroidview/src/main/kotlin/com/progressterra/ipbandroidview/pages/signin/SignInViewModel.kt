package com.progressterra.ipbandroidview.pages.signin

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class SignInViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val openUrlUseCase: OpenUrlUseCase
) : BaseViewModel<SignInState, SignInEffect>(), UseSignIn {

    override fun createInitialState() = SignInState()

    fun refresh() {
        emitState {
            it.copy(auth = it.auth.copy(enabled = false))
        }
    }

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
                postEffect(SignInEffect.Next(it))
            }.onFailure {
                postEffect(SignInEffect.Toast(R.string.wrong_phone))
            }
        }
    }
}
