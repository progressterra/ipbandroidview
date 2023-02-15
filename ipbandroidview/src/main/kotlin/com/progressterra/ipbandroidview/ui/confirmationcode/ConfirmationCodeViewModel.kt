package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.ConfirmationCodeComponentInteractor
import com.progressterra.ipbandroidview.composable.component.ConfirmationCodeComponentState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.user.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.NeedDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.StartVerificationChannelUseCase
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
    private val checkUserDetails: Boolean,
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val needDetailsUseCase: NeedDetailsUseCase
) : ViewModel(), ContainerHost<ConfirmationCodeComponentState, ConfirmationCodeEffect>,
    ConfirmationCodeComponentInteractor {

    override val container: Container<ConfirmationCodeComponentState, ConfirmationCodeEffect> =
        container(
            ConfirmationCodeComponentState()
        )

    init {
        startTimer()
    }

    fun setPhoneNumber(phoneNumber: String) = intent {
        reduce { state.copy(phoneNumber = phoneNumber) }
    }

    override fun resend() = intent {
        startVerificationChannelUseCase(state.phoneNumber)
        reduce { state.copy(code = "") }
        startTimer()
    }

    override fun onNext() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        endVerificationChannelUseCase(state.phoneNumber, state.code).onSuccess {
            fetchUserUseCase().onSuccess {
                needDetailsUseCase().onSuccess {
                    reduce { state.copy(screenState = ScreenState.SUCCESS) }
                    if (it && checkUserDetails)
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

    override fun codeChanged(code: String) = blockingIntent {
        if (code.length <= 4) reduce { state.copy(code = code) }
        if (code.length == 4) onNext()
    }

    private fun startTimer() = intent {
        reduce { state.copy(canResend = false) }
        for (i in 45 downTo 1) {
            delay(1000)
            if (i >= 10) reduce { state.copy(timer = "00:$i") }
            else reduce { state.copy(timer = "00:0$i") }
        }
        reduce { state.copy(canResend = true, timer = "") }
    }

    override fun onBack() = intent {
        postSideEffect(ConfirmationCodeEffect.Back)
    }
}