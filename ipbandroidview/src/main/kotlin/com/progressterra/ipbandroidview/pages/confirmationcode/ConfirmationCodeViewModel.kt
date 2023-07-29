package com.progressterra.ipbandroidview.pages.confirmationcode

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.code.CodeEvent
import com.progressterra.ipbandroidview.features.countdown.CountdownEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import kotlinx.coroutines.delay

class ConfirmationCodeViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase
) : BaseViewModel<ConfirmationCodeState, ConfirmationCodeEvent>(), UseConfirmationCode {

    override fun createInitialState() = ConfirmationCodeState()

    fun refresh(phoneNumber: String) {
        emitState { it.copy(code = it.code.copy(code = "", phone = phoneNumber)) }
        startTimer()
    }

    private fun onNext() {
        onBackground {
            this.emitState { it.copy(code = it.code.copy(enabled = false)) }
            val call = endVerificationChannelUseCase(
                currentState.code.phone,
                currentState.code.code
            ).onSuccess {
                postEffect(ConfirmationCodeEvent.Next)
            }.onFailure {
                postEffect(ConfirmationCodeEvent.Toast(R.string.wrong_code))
            }
            this.emitState { it.copy(code = it.code.copy(enabled = call.isSuccess)) }
        }
    }

    private fun startTimer() {
        onBackground {
            this.emitState { it.copy(repeat = it.repeat.copy(enabled = false)) }
            for (i in 45.downTo(1)) {
                delay(1000)
                this.emitState { it.copy(repeat = it.repeat.copy(count = if (i >= 10) "00:$i" else "00:0$i")) }
            }
            this.emitState { it.copy(repeat = it.repeat.copy(enabled = true)) }
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
        postEffect(ConfirmationCodeEvent.Back)
    }
}
