package com.progressterra.ipbandroidview.ui.audits

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.domain.AllDocumentsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AuditsViewModel(
    private val allDocumentsUseCase: AllDocumentsUseCase
) : ViewModel(), ContainerHost<AuditsState, AuditsEffect>, AuditsInteractor {

    override val container: Container<AuditsState, AuditsEffect> = container(AuditsState())

    init {
        fetch()
    }

    private fun fetch() = intent {
        allDocumentsUseCase.allDocuments().map {
            reduce {
                state.copy(documents = it)
            }
        }
    }

    override fun onDocumentDetails(id: String) = intent {
        postSideEffect(AuditsEffect.OnDocumentDetails(id))
    }

    override fun onAudit() = intent {
        postSideEffect(AuditsEffect.OnAudit)
    }
}