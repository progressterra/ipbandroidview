package com.progressterra.ipbandroidview.pages.signin

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.shared.isRussianPhoneNumber
import com.progressterra.ipbandroidview.shared.isTestPhoneNumber
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class SignInViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val openUrlUseCase: OpenUrlUseCase
) : ViewModel(), ContainerHost<SignInState, SignInEffect>, UseSignIn {

    override val container: Container<SignInState, SignInEffect> = container(
        SignInState(
            authOrSkipState = AuthOrSkipState(
                auth = ButtonState(
                    id = "next", enabled = false
                ), skip = ButtonState(
                    id = "skip"
                )
            )
        )
    )

    override fun handle(event: ButtonEvent) = intent {
        when (event.id) {
            "next" -> when (event) {
                is ButtonEvent.Click -> onNext()
            }

            "skip" -> when (event) {
                is ButtonEvent.Click -> postSideEffect(SignInEffect.Skip)
            }
        }
    }

    override fun handle(event: LinkTextEvent) = intent {
        when (event) {
            is LinkTextEvent.Click -> openUrlUseCase(event.url)
        }
    }

    override fun handle(event: TopBarEvent) = intent {
        when (event) {
            is TopBarEvent.Back -> Unit
        }
    }

    override fun handle(event: TextFieldEvent) = blockingIntent {
        when (event) {
            is TextFieldEvent.TextChanged -> {
                if (event.text.length <= 11) {
                    val valid = event.text.isRussianPhoneNumber() || event.text.isTestPhoneNumber()
                    if (event.text.length == 1 && event.text.first() == '8') {
                        reduce { state.uPhoneText("7") }
                    } else {
                        reduce { state.uPhoneText(event.text) }
                    }
                    reduce { state.uPhoneValid(valid).uAuthButton(valid) }
                }
            }

            is TextFieldEvent.Action -> onNext()
            is TextFieldEvent.AdditionalAction -> Unit
        }
    }

    private fun onNext() = intent {
        startVerificationChannelUseCase(state.phone.text).onSuccess {
            postSideEffect(SignInEffect.Next(it))
        }.onFailure {
            postSideEffect(SignInEffect.Toast(R.string.wrong_phone))
        }
    }
}