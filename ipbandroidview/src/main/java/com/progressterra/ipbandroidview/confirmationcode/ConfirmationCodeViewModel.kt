package com.progressterra.ipbandroidview.confirmationcode

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class ConfirmationCodeViewModel : ViewModel(),
    ContainerHost<ConfirmationCodeState, ConfirmationEffect>, ConfirmationCodeInteractor {

    override val container: Container<ConfirmationCodeState, ConfirmationEffect> = container(
        ConfirmationCodeState()
    )

    override fun onBack() = intent {
        postSideEffect(ConfirmationEffect.Back)
    }

    override fun onResend() {
        TODO()
    }

    override fun onNext() = intent {
        postSideEffect(ConfirmationEffect.Next)
    }

    override fun onCode(code: String) {
        TODO("Not yet implemented")
    }
}