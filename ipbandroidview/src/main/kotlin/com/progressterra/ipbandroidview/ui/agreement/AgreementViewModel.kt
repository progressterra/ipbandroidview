package com.progressterra.ipbandroidview.ui.agreement

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.domain.usecase.OpenMailToUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class AgreementViewModel(
    private val openMailToUseCase: OpenMailToUseCase
) : ViewModel(),
    ContainerHost<AgreementState, AgreementEvent>, UseAgreement {

    override val container = container<AgreementState, AgreementEvent>(AgreementState())

    override fun onBack() {
        intent {
            postSideEffect(AgreementEvent.Back)
        }
    }

    override fun mailTo(email: String) {
        intent {
            openMailToUseCase(email)
        }
    }
}