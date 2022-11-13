package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.domain.usecase.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.StartVerificationChannelUseCase
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ConfirmationCodeViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase
) : ViewModel(), ContainerHost<ConfirmationCodeState, ConfirmationCodeEffect> {

    override val container: Container<ConfirmationCodeState, ConfirmationCodeEffect> = container(
        ConfirmationCodeState()
    )

    init {
        startTimer()
    }

    fun setPhoneNumber(phoneNumber: String) = intent {
        reduce { state.copy(phoneNumber = phoneNumber) }
    }

    fun resend() {
        intent {
            startVerificationChannelUseCase.start(state.phoneNumber)
            reduce { state.copy(code = "") }
        }
        startTimer()
    }

    fun next() = intent {
        endVerificationChannelUseCase.end(state.phoneNumber, state.code).onSuccess {
            postSideEffect(ConfirmationCodeEffect.Next)
        }.onFailure {
            postSideEffect(ConfirmationCodeEffect.Toast(R.string.wrong_code))
        }
    }

    fun editCode(code: String) = intent {
        if (code.length <= 4) reduce { state.copy(code = code) }
        if (code.length == 4) next()
    }

    private fun startTimer() = intent {
        reduce { state.copy(isTimer = true) }
        for (i in 45 downTo 1) {
            delay(1000)
            if (i >= 10) reduce { state.copy(timer = "00:$i") }
            else reduce { state.copy(timer = "00:0$i") }
        }
        reduce { state.copy(isTimer = false) }
    }

    fun back() = intent {
        postSideEffect(ConfirmationCodeEffect.Back)
    }
}