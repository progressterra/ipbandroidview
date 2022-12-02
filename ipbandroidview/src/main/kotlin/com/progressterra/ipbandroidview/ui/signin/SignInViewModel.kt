package com.progressterra.ipbandroidview.ui.signin

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.domain.usecase.StartVerificationChannelUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SignInViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase
) : ViewModel(), ContainerHost<SignInState, SignInEffect> {

    override val container: Container<SignInState, SignInEffect> = container(SignInState())

    fun next() = intent {
        startVerificationChannelUseCase(state.phoneNumber.trim()).onSuccess {
            postSideEffect(SignInEffect.Next(state.phoneNumber))
        }.onFailure {
            postSideEffect(SignInEffect.Toast(R.string.wrong_phone))
        }
    }

    fun skip() = intent { postSideEffect(SignInEffect.Skip) }

    fun editPhoneNumber(phoneNumber: String) = intent {
        reduce { state.copy(phoneNumber = phoneNumber) }
    }
}