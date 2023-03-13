package com.progressterra.ipbandroidview.ui.documents

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.checklist.AllDocumentsUseCase
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.ChecklistStatus
import com.progressterra.ipbandroidview.model.Document
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DocumentsViewModel(
    private val allDocumentsUseCase: AllDocumentsUseCase
) : ViewModel(), ContainerHost<DocumentsState, DocumentsEffect>, DocumentsInteractor {

    override val container: Container<DocumentsState, DocumentsEffect> = container(
        DocumentsState()
    )

    init {
        refresh()
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        allDocumentsUseCase().onSuccess {
            val finished = it.filter { doc -> doc.isFinished() && !doc.isRecentlyFinished }
            val unfinished =
                it.filter { doc -> !doc.isFinished() || doc.isRecentlyFinished }.reversed()
            reduce {
                state.copy(
                    documents = unfinished,
                    archivedDocuments = finished,
                    screenState = ScreenState.SUCCESS
                )
            }
            postSideEffect(DocumentsEffect.UpdateCounter(unfinished.size))
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }


    override fun openDocument(document: Document) = intent {
        postSideEffect(
            DocumentsEffect.OpenChecklist(
                AuditDocument(
                    checklistId = document.checklistId,
                    placeId = document.placeId,
                    documentId = document.documentId,
                    name = document.name
                ), if (document.isFinished()) ChecklistStatus.READ_ONLY else ChecklistStatus.ONGOING
            )
        )
    }
}