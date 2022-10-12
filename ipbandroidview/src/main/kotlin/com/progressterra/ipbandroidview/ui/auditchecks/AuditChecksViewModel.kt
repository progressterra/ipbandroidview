package com.progressterra.ipbandroidview.ui.auditchecks

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class AuditChecksViewModel : ViewModel(), ContainerHost<AuditChecksState, AuditChecksEffect>,
    AuditChecksInteractor {

    override val container: Container<AuditChecksState, AuditChecksEffect> = container(
        AuditChecksState()
    )

    override fun onCheck(check: Check) = intent {
        postSideEffect(AuditChecksEffect.OnCheck(check))
    }

    override fun onBack() = intent {
        postSideEffect(AuditChecksEffect.OnBack)
    }

    override fun onStart() = intent {

    }

    override fun onStop() = intent {

    }
}