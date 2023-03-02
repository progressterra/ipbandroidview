package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.ButtonEvent
import com.progressterra.ipbandroidview.composable.component.ButtonState
import com.progressterra.ipbandroidview.composable.component.ConfirmationCodeEvent
import com.progressterra.ipbandroidview.composable.component.ConfirmationCodeState
import com.progressterra.ipbandroidview.composable.component.TextButtonEvent
import com.progressterra.ipbandroidview.composable.component.TextButtonState
import com.progressterra.ipbandroidview.composable.component.UseConfirmationCode
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.domain.usecase.user.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.StartVerificationChannelUseCase
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class ConfirmationCodeViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase,
    private val manageResources: ManageResources
) : ViewModel(), ContainerHost<ConfirmationCodeState, ConfirmationCodeEffect>, UseConfirmationCode {

    override val container: Container<ConfirmationCodeState, ConfirmationCodeEffect> = container(
        ConfirmationCodeState(
            resendButton = TextButtonState(
                text = manageResources.string(R.string.resend)
            ), nextButton = ButtonState(
                text = manageResources.string(R.string.next)
            )
        )
    )

    fun refresh() = intent {
        reduce { state.copy(code = "") }
        startTimer()
    }

    fun setPhoneNumber(phoneNumber: String) = intent {
        reduce { state.copy(phoneNumber = phoneNumber) }
    }

    private fun onNext() = intent {
        reduce {
            val newNextButton = state.nextButton.updateEnabled(false)
            val newResendButton = state.resendButton.updateEnabled(false)
            state.copy(
                nextButton = newNextButton, resendButton = newResendButton
            )
        }
        var isSuccess = true
        endVerificationChannelUseCase(state.phoneNumber, state.code).onSuccess {
            postSideEffect(ConfirmationCodeEffect.Next)
        }.onFailure {
            isSuccess = false
            postSideEffect(ConfirmationCodeEffect.Toast(R.string.wrong_code))
        }
        reduce {
            val newNextButton = state.nextButton.updateEnabled(isSuccess)
            val newResendButton = state.resendButton.updateEnabled(isSuccess)
            state.copy(
                nextButton = newNextButton, resendButton = newResendButton
            )
        }

    }

    private fun codeChanged(code: String) = blockingIntent {
        if (code.length <= 4) reduce { state.copy(code = code) }
        if (code.length == 4) onNext()
    }

    private fun startTimer() = intent {
        reduce {
            val newResendButton = state.resendButton.updateEnabled(false)
            state.copy(resendButton = newResendButton)
        }
        for (i in 45 downTo 1) {
            delay(1000)
            if (i >= 10) reduce {
                val newResendButton = state.resendButton.updateText("00:$i")
                state.copy(resendButton = newResendButton)
            }
            else reduce {
                val newResendButton = state.resendButton.updateText("00:0$i")
                state.copy(resendButton = newResendButton)
            }
        }
        reduce {
            val newResendButton = state.resendButton.updateEnabled(true)
                .updateText(manageResources.string(R.string.resend))
            state.copy(resendButton = newResendButton)
        }
    }

    private fun onBack() = intent {
        postSideEffect(ConfirmationCodeEffect.Back)
    }

    override fun handleEvent(
        id: String, event: ConfirmationCodeEvent
    ) {
        when (id) {
            "main" -> {
                when (event) {
                    is ConfirmationCodeEvent.CodeChanged -> codeChanged(event.code)
                    is ConfirmationCodeEvent.Back -> onBack()
                }
            }
        }
    }

    override fun handleEvent(id: String, event: ButtonEvent) = intent {
        when (id) {
            "next" -> onNext()
        }
    }

    override fun handleEvent(id: String, event: TextButtonEvent) = intent {
        when (id) {
            "resend" -> {
                startVerificationChannelUseCase(state.phoneNumber)
                reduce { state.copy(code = "") }
                startTimer()
            }
        }
    }
}