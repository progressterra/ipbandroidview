package com.progressterra.ipbandroidview.ui.documents

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.dto.Checklist
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
        refresh()
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        allDocumentsUseCase.allDocuments().onSuccess {
            reduce {
                state.copy(documents = it, screenState = ScreenState.SUCCESS)
            }
            postSideEffect(DocumentsEffect.UpdateCounter(it.filter { doc -> doc.finishDate == null }.size))
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }


    override fun openDocument(document: Document) = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        documentChecklistUseCase.documentChecklist(document.documentId).onSuccess {
            postSideEffect(
                DocumentsEffect.OpenChecklist(
                    Checklist(
                        checklistId = document.checklistId,
                        placeId = document.placeId,
                        name = document.name,
                        checks = it,
                        done = document.finishDate != null,
                        ongoing = document.finishDate == null,
                        documentId = document.documentId
                    )
                )
            )
            reduce { state.copy(screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun openOrganizations() = intent {
        postSideEffect(DocumentsEffect.OpenOrganizations)
    }
}