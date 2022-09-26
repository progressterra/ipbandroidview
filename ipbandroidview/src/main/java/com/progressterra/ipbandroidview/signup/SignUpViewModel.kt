package com.progressterra.ipbandroidview.signup

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class SignUpViewModel : ViewModel(), ContainerHost<SignUpState, SignUpEffect>, SignUpInteractor {

    override val container: Container<SignUpState, SignUpEffect> = container(SignUpState())

    override fun onBack() = intent {
        postSideEffect(SignUpEffect.Back)
    }

    override fun onSkip() = intent {
        postSideEffect(SignUpEffect.Skip)
    }

    override fun onNext() = intent {
        postSideEffect(SignUpEffect.Next)
    }

    override fun onBirthday(birthday: String) {
        TODO("Not yet implemented")
    }

    override fun onEmail(email: String) {
        TODO("Not yet implemented")
    }

    override fun onName(name: String) {
        TODO("Not yet implemented")
    }
}