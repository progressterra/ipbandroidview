package com.progressterra.ipbandroidview.ui.documents

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.processes.usecase.checklist.AllDocumentsUseCase
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
    private val allDocumentsUseCase: AllDocumentsUseCase,
    private val manageResources: ManageResources
) : ViewModel(), ContainerHost<DocumentsState, DocumentsEffect>, DocumentsInteractor {

    override val container: Container<DocumentsState, DocumentsEffect> = container(
        DocumentsState(
            archiveButton = ButtonState(
                text = manageResources.string(R.string.to_archive)
            )
        )
    )

    init {
        refresh()
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        allDocumentsUseCase().onSuccess {
            val finished = it.filter { doc -> doc.isFinished() }
            val unfinished = it.filter { doc -> !doc.isFinished() }.reversed()
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

    override fun handle(event: ButtonEvent) = intent {
        when (id) {
            "archive" -> when (event) {
                ButtonEvent.Click -> postSideEffect(
                    DocumentsEffect.Archive(
                        manageResources.string(R.string.archived),
                        state.archivedDocuments
                    )
                )
            }
        }
    }
}