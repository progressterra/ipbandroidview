package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.FetchUserUseCase
import com.progressterra.ipbandroidview.domain.usecase.NeedDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.StartVerificationChannelUseCase
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ConfirmationCodeViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val needDetailsUseCase: NeedDetailsUseCase
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
        reduce { state.copy(screenState = ScreenState.LOADING) }
        endVerificationChannelUseCase.end(state.phoneNumber, state.code).onSuccess {
            fetchUserUseCase.fetch().onSuccess {
                needDetailsUseCase.needDetails().onSuccess {
                    reduce { state.copy(screenState = ScreenState.SUCCESS) }
                    if (it)
                        postSideEffect(ConfirmationCodeEffect.NeedDetails)
                    else
                        postSideEffect(ConfirmationCodeEffect.SkipDetails)
                }.onFailure {
                    reduce { state.copy(screenState = ScreenState.ERROR) }
                    postSideEffect(ConfirmationCodeEffect.Toast(R.string.try_again))
                }
            }.onFailure {
                reduce { state.copy(screenState = ScreenState.ERROR) }
                postSideEffect(ConfirmationCodeEffect.Toast(R.string.try_again))
            }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
            postSideEffect(ConfirmationCodeEffect.Toast(R.string.wrong_code))
        }
    }

    fun editCode(code: String) = intent {
        if (code.length <= 4) reduce { state.copy(code = code) }
        if (code.length == 4) next()
    }

    private fun startTimer() = intent {
        reduce { state.copy(canResend = true) }
        for (i in 45 downTo 1) {
            delay(1000)
            if (i >= 10) reduce { state.copy(timer = "00:$i") }
            else reduce { state.copy(timer = "00:0$i") }
        }
        reduce { state.copy(canResend = false) }
    }

    fun back() = intent {
        postSideEffect(ConfirmationCodeEffect.Back)
    }
}