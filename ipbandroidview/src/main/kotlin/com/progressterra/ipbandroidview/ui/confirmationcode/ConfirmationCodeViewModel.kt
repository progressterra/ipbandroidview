package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.R
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
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase
) : ViewModel(), ContainerHost<ConfirmationCodeState, ConfirmationEffect>,
    ConfirmationCodeInteractor {

    override val container: Container<ConfirmationCodeState, ConfirmationEffect> = container(
        ConfirmationCodeState(phoneNumber = UserData.phone)
    )

    init {
        startTimer()
    }

    override fun onResend() {
        intent {
            startVerificationChannelUseCase.start(state.phoneNumber)
            reduce { state.copy(code = "") }
        }
        startTimer()
    }

    override fun onNext() = intent {
        endVerificationChannelUseCase.end(state.phoneNumber, state.code).onSuccess {
            postSideEffect(ConfirmationEffect.OnNext)
        }.onFailure {
            postSideEffect(ConfirmationEffect.OnToast(R.string.wrong_code))
        }
    }

    override fun onCode(code: String) = intent {
        if (code.length <= 4) reduce { state.copy(code = code) }
        if (code.length == 4) onNext()
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

    override fun onBack() = intent {
        postSideEffect(ConfirmationEffect.OnBack)
    }
}