package com.progressterra.ipbandroidview.signin

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.domain.startverification.StartVerificationChannelUseCase
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

    override fun onNext() = intent {
        if (startVerificationChannelUseCase.start(state.phoneNumber)) {
            UserData.phone = state.phoneNumber
            postSideEffect(SignInEffect.Next)
        } else
            postSideEffect(SignInEffect.Toast(R.string.wrong_phone))
    }

    override fun onSkip() = intent {
        postSideEffect(SignInEffect.Skip)
    }

    override fun onBack() = intent {
        postSideEffect(SignInEffect.Back)
    }

    override fun onPhoneNumber(phoneNumber: String) = intent {
        reduce {
            state.copy(phoneNumber = phoneNumber)
        }
    }
}