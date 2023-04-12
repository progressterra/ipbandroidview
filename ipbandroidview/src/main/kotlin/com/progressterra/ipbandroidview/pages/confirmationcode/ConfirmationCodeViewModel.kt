package com.progressterra.ipbandroidview.pages.confirmationcode

import android.util.Log
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.code.CodeEvent
import com.progressterra.ipbandroidview.features.countdown.CountdownEvent
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBarEvent
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
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
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase
) : ViewModel(), ContainerHost<ConfirmationCodeState, ConfirmationCodeEvent>, UseConfirmationCode {

    override val container: Container<ConfirmationCodeState, ConfirmationCodeEvent> =
        container(ConfirmationCodeState())

    fun refresh(phoneNumber: String) = intent {
        reduce { state.updateCode("").updatePhoneNumber(phoneNumber) }
        startTimer()
    }

    private fun onNext() = intent {
        reduce { state.updateCodeEnabled(false).updateNextEnabled(false) }
        var isSuccess = true
        endVerificationChannelUseCase(state.code.phone, state.code.code).onSuccess {
            postSideEffect(ConfirmationCodeEvent.Next)
        }.onFailure {
            Log.e("ConfirmationCodeViewModel", "onNext: ", it)
            isSuccess = false
            postSideEffect(ConfirmationCodeEvent.Toast(R.string.wrong_code))
        }
        reduce { state.updateCodeEnabled(isSuccess).updateNextEnabled(isSuccess) }
    }

    private fun startTimer() = intent {
        reduce { state.updateRepeatEnabled(false) }
        for (i in 45.downTo(1)) {
            delay(1000)
            reduce { state.updateRepeatCount(if (i >= 10) "00:$i" else "00:0$i") }
        }
        reduce { state.updateRepeatEnabled(true) }
    }

    override fun handle(event: CodeEvent) = blockingIntent {
        when (event) {
            is CodeEvent.Changed -> {
                if (event.code.length <= 4) reduce { state.updateCode(event.code) }
                if (event.code.length == 4) onNext()
            }
        }
        state.updateNextEnabled(state.code.code.length == 4)
    }

    override fun handle(event: ButtonEvent) = intent {
        when (event) {
            is ButtonEvent.Click -> onNext()
        }
    }

    override fun handle(event: CountdownEvent) = intent {
        when (event) {
            is CountdownEvent.Click -> {
                startVerificationChannelUseCase(state.code.phone)
                startTimer()
            }
        }
    }

    override fun handle(event: ProshkaTopBarEvent) = intent {
        when (event) {
            is ProshkaTopBarEvent.Back -> postSideEffect(ConfirmationCodeEvent.Back)
        }
    }
}