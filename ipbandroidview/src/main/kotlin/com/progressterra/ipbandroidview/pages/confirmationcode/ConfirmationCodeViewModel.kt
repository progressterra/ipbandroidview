package com.progressterra.ipbandroidview.pages.confirmationcode

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.code.CodeEvent
import com.progressterra.ipbandroidview.features.code.code
import com.progressterra.ipbandroidview.features.code.enabled
import com.progressterra.ipbandroidview.features.code.phone
import com.progressterra.ipbandroidview.features.countdown.CountdownEvent
import com.progressterra.ipbandroidview.features.countdown.count
import com.progressterra.ipbandroidview.features.countdown.enabled
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
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

    fun refresh(phoneNumber: String) {
        intent {
            reduce { ConfirmationCodeState.code.code.set(state, "") }
            reduce { ConfirmationCodeState.code.phone.set(state, phoneNumber) }
            startTimer()
        }
    }

    private fun onNext() {
        intent {
            reduce { ConfirmationCodeState.code.enabled.set(state, false) }
            val call = endVerificationChannelUseCase(state.code.phone, state.code.code).onSuccess {
                postSideEffect(ConfirmationCodeEvent.Next)
            }.onFailure {
                postSideEffect(ConfirmationCodeEvent.Toast(R.string.wrong_code))
            }
            reduce { ConfirmationCodeState.code.enabled.set(state, call.isSuccess) }
        }
    }

    private fun startTimer() {
        intent {
            reduce { ConfirmationCodeState.repeat.enabled.set(state, false) }
            for (i in 45.downTo(1)) {
                delay(1000)
                reduce {
                    ConfirmationCodeState.repeat.count.set(
                        state,
                        if (i >= 10) "00:$i" else "00:0$i"
                    )
                }
            }
            reduce { ConfirmationCodeState.repeat.enabled.set(state, true) }
        }
    }

    override fun handle(event: CodeEvent) {
        blockingIntent {
            reduce { ConfirmationCodeState.code.code.set(state, event.code) }
            if (event.code.length == 4) onNext()
        }
    }

    override fun handle(event: CountdownEvent) {
        intent {
            startVerificationChannelUseCase(state.code.phone)
            startTimer()
        }
    }

    override fun handle(event: TopBarEvent) {
        intent { postSideEffect(ConfirmationCodeEvent.Back) }
    }
}
