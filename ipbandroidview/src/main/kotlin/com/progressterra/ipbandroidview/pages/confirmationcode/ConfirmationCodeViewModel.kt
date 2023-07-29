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

    override val initialState = ConfirmationCodeState()

    fun refresh(phoneNumber: String) {
        fastEmitState { it.copy(code = it.code.copy(code = "", phone = phoneNumber)) }
        startTimer()
    }

    private fun onNext() {
        onBackground {
            emitState { it.copy(code = it.code.copy(enabled = false)) }
            val call = endVerificationChannelUseCase(
                state.value.code.phone,
                state.value.code.code
            ).onSuccess {
                postEffect(ConfirmationCodeEvent.Next)
            }.onFailure {
                postEffect(ConfirmationCodeEvent.Toast(R.string.wrong_code))
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
        fastEmitState {
            it.copy(code = it.code.copy(code = event.code))
        }
        if (event.code.length == 4) onNext()
    }

    override fun handle(event: CountdownEvent) {
        onBackground {
            startVerificationChannelUseCase(state.value.code.phone)
            startTimer()
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(ConfirmationCodeEvent.Back)
    }
}
