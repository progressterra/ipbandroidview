package com.progressterra.ipbandroidview.ui.audits

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
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
        reduce { state.copy(screenState = ScreenState.LOADING) }
        allDocumentsUseCase.allDocuments().onSuccess {
            reduce {
                state.copy(documents = it, screenState = ScreenState.SUCCESS)
            }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    override fun onRefresh() {
        fetch()
    }

    override fun onDocumentChecklist(document: Document) = intent {
        postSideEffect(AuditsEffect.OnDocumentDetails(document))
    }

    override fun onAudit() = intent {
        postSideEffect(AuditsEffect.OnOrganizations)
    }
}