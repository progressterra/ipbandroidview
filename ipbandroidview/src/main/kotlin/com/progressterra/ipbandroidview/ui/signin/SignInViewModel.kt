package com.progressterra.ipbandroidview.ui.signin

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.AuthComponentInteractor
import com.progressterra.ipbandroidview.composable.component.AuthComponentState
import com.progressterra.ipbandroidview.domain.usecase.OpenUrlUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.StartVerificationChannelUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class SignInViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val openUrlUseCase: OpenUrlUseCase
) : ViewModel(), ContainerHost<AuthComponentState, SignInEffect>, AuthComponentInteractor {

    override val container: Container<AuthComponentState, SignInEffect> = container(
        AuthComponentState(
            phoneNumber = "7"
        )
    )

    override fun onNext() = intent {
        startVerificationChannelUseCase(state.phoneNumber).onSuccess {
            postSideEffect(SignInEffect.Next(state.phoneNumber))
        }.onFailure {
            postSideEffect(SignInEffect.Toast(R.string.wrong_phone))
        }
    }

    override fun onSkip() = intent { postSideEffect(SignInEffect.Skip) }

    override fun editPhoneNumber(phoneNumber: String) = blockingIntent {
        reduce { state.copy(phoneNumber = phoneNumber) }
        checkPhoneNumber()
    }

    private fun checkPhoneNumber() = intent {
        reduce { state.copy(isDataValid = state.phoneNumber.isDigitsOnly()) }
    }

    override fun onUrlClick(url: String) = intent { openUrlUseCase(url) }
}