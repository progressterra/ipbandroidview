package com.progressterra.ipbandroidview.ui.signin

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.domain.StartVerificationChannelUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SignInViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase
) : ViewModel(), ContainerHost<SignInState, SignInEffect>, SignInInteractor {

    override val container: Container<SignInState, SignInEffect> = container(SignInState())

    override fun next() = intent {
        startVerificationChannelUseCase.start(state.phoneNumber.trim()).onSuccess {
            postSideEffect(SignInEffect.OpenNext(state.phoneNumber))
        }.onFailure {
            postSideEffect(SignInEffect.ShowToast(R.string.wrong_phone))
        }
    }

    override fun skip() = intent { postSideEffect(SignInEffect.Skip) }

    override fun editPhoneNumber(phoneNumber: String) = intent {
        reduce { state.copy(phoneNumber = phoneNumber) }
    }
}