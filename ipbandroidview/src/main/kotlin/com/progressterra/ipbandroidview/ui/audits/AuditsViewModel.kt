package com.progressterra.ipbandroidview.ui.audits

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class AuditsViewModel(

) : ViewModel(), ContainerHost<AuditsState, AuditsEffect>, AuditsInteractor {

    override val container: Container<AuditsState, AuditsEffect> = container(AuditsState())

    override fun onDocumentDetails(id: String) = intent {
        postSideEffect(AuditsEffect.OnDocumentDetails(id))
    }

    override fun onAudit() = intent {
        postSideEffect(AuditsEffect.OnAudit)
    }
}