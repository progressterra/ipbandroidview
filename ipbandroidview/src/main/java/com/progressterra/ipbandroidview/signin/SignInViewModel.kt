package com.progressterra.ipbandroidview.signin

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class SignInViewModel : ViewModel(), ContainerHost<SignInState, SignInEffect>, SignInInteractor {

    override val container: Container<SignInState, SignInEffect> = container(SignInState())

    override fun onNext() = intent {
        postSideEffect(SignInEffect.Next)
    }

    override fun onSkip() = intent {
        postSideEffect(SignInEffect.Skip)
    }

    override fun onBack() = intent {
        postSideEffect(SignInEffect.Back)
    }

    override fun onPhoneNumber(phoneNumber: String) {
        TODO("Not yet implemented")
    }
}