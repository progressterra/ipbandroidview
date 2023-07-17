package com.progressterra.ipbandroidview.pages.signin

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.shared.isRussianPhoneNumber
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

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "next" -> onNext()

                "skip" -> postSideEffect(SignInEffect.Skip)
            }
        }
    }

    override fun handle(event: LinkTextEvent) {
        intent {
            openUrlUseCase(event.url)
        }
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: TextFieldEvent) {
        blockingIntent {
            when (event) {
                is TextFieldEvent.TextChanged -> when (event.id) {
                    "phone" -> {
                        if (event.text.length <= 11) {
                            val valid = event.text.isRussianPhoneNumber()
                            reduce {
                                state.uPhoneText(event.text).uPhoneValid(valid).uAuthButton(valid)
                            }
                        }
                    }
                }

                is TextFieldEvent.Action -> when (event.id) {
                    "phone" -> onNext()
                }

                is TextFieldEvent.AdditionalAction -> when (event.id) {
                    "phone" -> Unit
                }
            }
        }
    }

    private fun onNext() {
        intent {
            startVerificationChannelUseCase(state.phone.formatByType()).onSuccess {
                postSideEffect(SignInEffect.Next(it))
            }.onFailure {
                postSideEffect(SignInEffect.Toast(R.string.wrong_phone))
            }
        }
    }
}
