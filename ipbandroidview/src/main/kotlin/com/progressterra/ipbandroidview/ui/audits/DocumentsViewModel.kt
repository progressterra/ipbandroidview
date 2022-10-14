package com.progressterra.ipbandroidview.ui.audits

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.AllDocumentsUseCase
import com.progressterra.ipbandroidview.domain.DocumentChecklistUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DocumentsViewModel(
    private val allDocumentsUseCase: AllDocumentsUseCase,
    private val documentChecklistUseCase: DocumentChecklistUseCase
) : ViewModel(), ContainerHost<DocumentsState, DocumentsEffect>, DocumentsInteractor {

    override val container: Container<DocumentsState, DocumentsEffect> = container(DocumentsState())

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
        reduce { state.copy(screenState = ScreenState.LOADING) }
        documentChecklistUseCase.documentChecklist(document.checklistId).onSuccess {
            postSideEffect(
                DocumentsEffect.OnChecklist(
                    Checklist(
                        checklistId = document.checklistId,
                        placeId = document.placeId,
                        name = document.name,
                        repetitiveness = "PLACEHOLDER",
                        lastTimeChecked = document.lastTimeChecked,
                        checks = it,
                        done = document.done
                    )
                )
            )
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun onAudit() = intent {
        postSideEffect(DocumentsEffect.OnOrganizations)
    }
}