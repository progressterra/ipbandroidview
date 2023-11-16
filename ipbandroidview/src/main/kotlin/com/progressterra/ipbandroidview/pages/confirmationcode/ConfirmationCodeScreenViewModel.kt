package com.progressterra.ipbandroidview.pages.confirmationcode

import com.progressterra.ipbandroidview.entities.SignInData
import com.progressterra.ipbandroidview.features.code.CodeEvent
import com.progressterra.ipbandroidview.features.countdown.CountdownEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.auth.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class ConfirmationCodeScreenViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase
) : AbstractInputViewModel<SignInData, ConfirmationCodeScreenState, ConfirmationCodeScreenEffect>(),
    UseConfirmationCodeScreen {

    override fun createInitialState() = ConfirmationCodeScreenState()

    private var timerJob: Job? = null

    override fun setup(data: SignInData) {
        emitState { it.copy(code = it.code.copy(code = "", phone = data.phone), signInData = data) }
        startTimer()
    }

    private fun onNext() {
        onBackground {
            emitState { it.copy(code = it.code.copy(enabled = false)) }
            val call = endVerificationChannelUseCase(
                currentState.signInData.token,
                currentState.signInData.phone,
                currentState.code.code
            ).onSuccess {
                postEffect(ConfirmationCodeScreenEffect.Next)
            }
            emitState { it.copy(code = it.code.copy(enabled = call.isSuccess)) }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = onBackground {
            emitState { it.copy(repeat = it.repeat.copy(enabled = false)) }
            if (currentState.signInData.allowedAttempts >= 0) {
                for (i in currentState.signInData.secondsToResend.downTo(1)) {
                    emitState { it.copy(repeat = it.repeat.copy(count = if (i >= 10) "00:$i" else "00:0$i")) }
                    delay(1000)
                }
                emitState { it.copy(repeat = it.repeat.copy(enabled = true)) }
            }
        }
    }

    override fun handle(event: CodeEvent) {
        emitState {
            it.copy(code = it.code.copy(code = event.code))
        }
        if (event.code.length == 4) onNext()
    }

    override fun handle(event: CountdownEvent) {
        onBackground {
            startVerificationChannelUseCase(currentState.code.phone).onSuccess { data ->
                emitState { it.copy(signInData = data) }
                startTimer()
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(ConfirmationCodeScreenEffect.Back)
    }
}
