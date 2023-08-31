package com.progressterra.ipbandroidview.pages.confirmationcode

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.code.CodeEvent
import com.progressterra.ipbandroidview.features.countdown.CountdownEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import kotlinx.coroutines.delay

class ConfirmationCodeScreenViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase
) : AbstractInputViewModel<String, ConfirmationCodeScreenState, ConfirmationCodeScreenEffect>(),
    UseConfirmationCodeScreen {

    override fun createInitialState() = ConfirmationCodeScreenState()

    override fun setup(data: String) {
        emitState { it.copy(code = it.code.copy(code = "", phone = data)) }
        startTimer()
    }

    private fun onNext() {
        onBackground {
            emitState { it.copy(code = it.code.copy(enabled = false)) }
            val call = endVerificationChannelUseCase(
                currentState.code.phone,
                currentState.code.code
            ).onSuccess {
                postEffect(ConfirmationCodeScreenEffect.Next)
            }.onFailure {
                postEffect(ConfirmationCodeScreenEffect.Toast(R.string.wrong_code))
            }
            emitState { it.copy(code = it.code.copy(enabled = call.isSuccess)) }
        }
    }

    private fun startTimer() {
        onBackground {
            emitState { it.copy(repeat = it.repeat.copy(enabled = false)) }
            for (i in 45.downTo(1)) {
                delay(1000)
                emitState { it.copy(repeat = it.repeat.copy(count = if (i >= 10) "00:$i" else "00:0$i")) }
            }
            emitState { it.copy(repeat = it.repeat.copy(enabled = true)) }
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
            startVerificationChannelUseCase(currentState.code.phone)
            startTimer()
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(ConfirmationCodeScreenEffect.Back)
    }
}
