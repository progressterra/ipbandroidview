package com.progressterra.ipbandroidview.ui.auditchecks

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.ui.audits.Document
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AuditChecksViewModel(
    private val documentChecklistUseCase: DocumentChecklistUseCase
) : ViewModel(), ContainerHost<AuditChecksState, AuditChecksEffect>,
    AuditChecksInteractor {

    override val container: Container<AuditChecksState, AuditChecksEffect> = container(
        AuditChecksState()
    )

    @Suppress("unused")
    fun setDocument(document: Document) = intent {
        reduce {
            AuditChecksState(
                id = document.id,
                name = document.name,
                checkCounter = document.checkCounter,
                repetitiveness = document.repetitiveness,
                lastTimeChecked = document.lastTimeChecked,
                done = document.done,
                ongoing = false
            )
        }
        documentChecklistUseCase.documentChecklist(state.id, state.done).onSuccess {
            reduce { state.copy(checks = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun onRefresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        documentChecklistUseCase.documentChecklist(state.id, state.done).onSuccess {
            reduce { state.copy(checks = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun onCheck(check: Check) = intent {

    }

    override fun onBack() = intent {
        postSideEffect(AuditChecksEffect.OnBack)
    }

    override fun onStart() = intent {

    }

    override fun onStop() = intent {

    }

    override fun onSheetVisibilityChange(visibility: Boolean) {

    }

    override fun closeSheet() {

    }

    override fun yesNo(yes: Boolean) {

    }

    override fun onCheckCommentaryChange(commentary: String) {
        TODO("Not yet implemented")
    }
}