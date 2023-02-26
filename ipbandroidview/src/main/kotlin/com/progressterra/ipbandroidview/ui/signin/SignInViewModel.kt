package com.progressterra.ipbandroidview.ui.signin

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.AuthEvent
import com.progressterra.ipbandroidview.composable.component.AuthState
import com.progressterra.ipbandroidview.composable.component.ButtonEvent
import com.progressterra.ipbandroidview.composable.component.ButtonState
import com.progressterra.ipbandroidview.composable.component.TextButtonEvent
import com.progressterra.ipbandroidview.composable.component.TextButtonState
import com.progressterra.ipbandroidview.composable.component.TextFieldComponentEvent
import com.progressterra.ipbandroidview.composable.component.TextFieldState
import com.progressterra.ipbandroidview.composable.component.UseAuth
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.domain.usecase.OpenUrlUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.StartVerificationChannelUseCase
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
    private val openUrlUseCase: OpenUrlUseCase,
    manageResources: ManageResources
) : ViewModel(), ContainerHost<AuthState, SignInEffect>, UseAuth {

    override val container: Container<AuthState, SignInEffect> = container(
        AuthState(
            phone = TextFieldState(
                hint = manageResources.string(R.string.phone_number),
            ),
            next = ButtonState(
                text = manageResources.string(R.string.auth_button)
            ),
            skip = TextButtonState(
                text = manageResources.string(R.string.auth_skip)
            )
        )
    )

    private fun onNext() = intent {
        startVerificationChannelUseCase(state.phone.text).onSuccess {
            postSideEffect(SignInEffect.Next(state.phone.text.trim()))
        }.onFailure {
            postSideEffect(SignInEffect.Toast(R.string.wrong_phone))
        }
    }

    override fun handleEvent(id: String, event: AuthEvent) = intent {
        when (id) {
            "main" -> when (event) {
                is AuthEvent.UrlClick -> openUrlUseCase(event.url)
            }
        }
    }

    override fun handleEvent(id: String, event: ButtonEvent) = intent {
        when (id) {
            "next" -> when (event) {
                is ButtonEvent.Click -> onNext()
            }
        }
    }

    override fun handleEvent(id: String, event: TextButtonEvent) = intent {
        when (id) {
            "skip" -> when (event) {
                is TextButtonEvent.Click -> postSideEffect(SignInEffect.Skip)
            }
        }
    }

    override fun handleEvent(id: String, event: TextFieldComponentEvent) = blockingIntent {
        when (id) {
            "phone" -> when (event) {
                is TextFieldComponentEvent.TextChanged -> {
                    reduce {
                        val newNext =
                            state.next.updateEnabled(state.phone.text.isDigitsOnly())
                        state.copy(next = newNext).updatePhoneText(event.text)
                    }
                }
                is TextFieldComponentEvent.Action -> onNext()
            }
        }
    }
}