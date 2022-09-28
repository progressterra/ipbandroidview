package com.progressterra.ipbandroidview.confirmationcode

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.domain.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.StartVerificationChannelUseCase
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ConfirmationCodeViewModel(
    phoneNumber: String,
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase
) : ViewModel(),
    ContainerHost<ConfirmationCodeState, ConfirmationEffect>, ConfirmationCodeInteractor {

    override val container: Container<ConfirmationCodeState, ConfirmationEffect> = container(
        ConfirmationCodeState(phoneNumber = phoneNumber)
    )

    init {
        startTimer()
    }

    override fun onBack() = intent {
        postSideEffect(ConfirmationEffect.Back)
    }

    override fun onResend() = intent {
        startVerificationChannelUseCase.start(state.phoneNumber)
        startTimer()
    }

    override fun onNext() = intent {
        if (endVerificationChannelUseCase.end(state.phoneNumber, state.code)) {
            UserData.phone = state.phoneNumber
            postSideEffect(ConfirmationEffect.Next)
        }
    }

    override fun onCode(code: String) = intent {
        reduce {
            state.copy(code = code)
        }
    }

    private fun startTimer(seconds: Int = 45) = intent {
        reduce { state.copy(isTimer = true) }
        for (i in seconds downTo 1) {
            delay(1000)
            reduce {
                state.copy(timer = "00:$i")
            }
        }
        reduce { state.copy(isTimer = false) }
    }
}